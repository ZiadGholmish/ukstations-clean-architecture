package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.domain.models.arrivals.ArrivalTimeModel
import com.citymapper.app.domain.models.arrivals.StopArrivalsPayLoad
import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.models.stoppoint.NetworkHttpError
import com.citymapper.app.domain.models.stoppoint.StopPintsPayLoad
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.usecase.FetchArrivalTimesUseCase
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(private val fetchStopPointsUseCase: FetchStopPointsUseCase, private val fetchArrivalTimesUseCase: FetchArrivalTimesUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val arrivalTimesDisposable = CompositeDisposable()

    val stopPointsLiveData = MutableLiveData<List<StopPoint>>()
    val stopPointsNetworkHttpError = MutableLiveData<NetworkHttpError>()
    val stopPointsRequestState = MutableLiveData<RequestState>()

    private val stopTypes = listOf("NaptanMetroStation", "NaptanMetroPlatform", "NaptanMetroEntrance", "NaptanMetroAccessArea")
    private val radius = 1000

    /**
     * load the available stop points within 1 km from the user a
     */
    fun loadStopPointsByLocation(lat: Double, lon: Double) {
        compositeDisposable.add(
                fetchStopPointsUseCase
                        .fetchStopPoints(stopTypes.joinToString(), radius, lat, lon)
                        .doOnError { stopPointsRequestState.value = RequestState.Complete }
                        .doOnNext { stopPointsRequestState.value = RequestState.Complete }
                        .doOnSubscribe { stopPointsRequestState.value = RequestState.Loading }
                        .subscribe({ response ->
                            handleNetworkResult(response)
                        }, { error ->
                            stopPointsRequestState.value = RequestState.Complete
                            error.printStackTrace()
                        }))
    }

    /**
     * handle the response from login or check the error code
     */
    private fun handleNetworkResult(result: StopPointsResult) {
        return when (result) {
            is StopPintsPayLoad -> handleResult(result)
            is NetworkHttpError -> handleError(result)
        }
    }

    /**
     * handle the success result and check the envelop for messages or what
     */
    private fun handleResult(result: StopPintsPayLoad) {
        when (result) {
            is StopPintsPayLoad.Data -> {
                if (!result.data.isEmpty()) {
                    stopPointsLiveData.value = result.data
                    getArrivalTimesForStopPoints()
                } else stopPointsRequestState.value = RequestState.Complete
            }
        }
    }


    private fun getArrivalTimesForStopPoints() {
//        if (!arrivalTimesDisposable.isDisposed) {
//            arrivalTimesDisposable.dispose()
//        }
        stopPointsLiveData.value?.forEach {
            arrivalTimesDisposable.add(fetchArrivalTimesUseCase
                    .fetchStopPointArrivals(it.id)
                    .doOnError { it.printStackTrace() }
                    .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
                    .subscribe {
                        checkArrivalTimesData(it)
                    })
        }
    }

    private fun checkArrivalTimesData(result: StopArrivalsResult) {
        when (result) {
            is StopArrivalsPayLoad.Data -> updateStopPoints(result.data)
        }
    }

    private fun updateStopPoints(arrivalTimes: List<ArrivalTimeModel>) {
        val updatedList = stopPointsLiveData.value?.map {
            if (!arrivalTimes.isEmpty()) {
                if (arrivalTimes[0].naptanId == it.id) {
                    it.copy(arrivalsTimes = arrivalTimes.takeLast(3))
                } else {
                    it
                }
            } else {
                it
            }
        }

        stopPointsLiveData.value = updatedList
    }

    /**
     * handle the error code that come from the server
     */
    private fun handleError(result: NetworkHttpError) {
        stopPointsNetworkHttpError.value = result
    }

    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

}