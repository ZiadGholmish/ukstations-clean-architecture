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
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(private val fetchStopPointsUseCase: FetchStopPointsUseCase, private val fetchArrivalTimesUseCase: FetchArrivalTimesUseCase) : ViewModel() {

    //disposal to clear the old requests and clear resources then the vm is cleared
    private val compositeDisposable = CompositeDisposable()
    private var arrivalsTimeDisposable = CompositeDisposable()

    //live data to control the values
    val stopPointsLiveData = MutableLiveData<List<StopPoint>>()
    val arrivalTimesData = MutableLiveData<List<StopPoint>>()
    val stopPointsNetworkHttpError = MutableLiveData<NetworkHttpError>()
    val stopPointsRequestState = MutableLiveData<RequestState>()

    // api criteria --> it can come from the user inputs or the settings of the chosen city
    private val stopTypes = listOf("NaptanMetroStation", "NaptanMetroPlatform", "NaptanMetroEntrance", "NaptanMetroAccessArea")
    private val radius = 1000

    /**
     * load the available stop points within 1 km from the user a
     */
    fun loadStopPointsByLocation(lat: Double, lon: Double) {
        compositeDisposable.clear()
        val stopPointObservable = fetchStopPointsUseCase
                .fetchStopPoints(stopTypes.joinToString(), radius, lat, lon)
                .doOnError { stopPointsRequestState.value = RequestState.Complete }
                .doOnNext { stopPointsRequestState.value = RequestState.Complete }
                .doOnSubscribe { stopPointsRequestState.value = RequestState.Loading }
                .subscribe({ response ->
                    handleNetworkResult(response)
                }, { error ->
                    stopPointsRequestState.postValue(RequestState.Complete)
                    error.printStackTrace()
                })
        compositeDisposable.add(stopPointObservable)
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
                    arrivalTimesData.value = result.data
                    getArrivalTimesForStopPoints()
                } else stopPointsRequestState.value = RequestState.Complete
            }
        }
    }

    /**
     * create observable for stop point to call the arrival times and then
     * zip them so we can get the all responses in the same time then update the adapter one time
     *  ==we can ignore zip if we want to refresh every stop point separately==
     */
    private fun getArrivalTimesForStopPoints() {
        arrivalsTimeDisposable.clear()
        val arrivalTimesObservableList: List<Observable<StopArrivalsResult>>? = arrivalTimesData.value?.map {
            fetchArrivalTimesUseCase.fetchStopPointArrivals(it.id)
        }
        val zip = Observable.zip(arrivalTimesObservableList) { args1 ->
            val arrivals = mutableListOf<StopArrivalsResult>()
            args1.forEach { arrivals.add(it as StopArrivalsResult) }
            return@zip arrivals
        }
                .repeatWhen { completed -> completed.delay(30, TimeUnit.SECONDS) }
                .subscribe(
                        { arrivalsTimesResult ->
                            checkArrivalTimesData(arrivalsTimesResult)
                        },
                        {
                            it.printStackTrace()
                        })
        arrivalsTimeDisposable.add(zip)
    }


    /**
     * handle the arrival time success items to refresh the data
     * if there is error i am ignore it for now
     */
    private fun checkArrivalTimesData(result: List<StopArrivalsResult>) {
        val updatedMap = result.map {
            if (it is StopArrivalsPayLoad.Data) {
                return@map it.data
            } else {
                null
            }
        }.filter { it != null }
        updateStopPoints(updatedMap)
    }

    /**
     * check the stop points and update the arrival times inside stop point
     * then notify the live data to update the views
     *
     * ==i have to handle the case when list contains items then became empty=====
     */
    private fun updateStopPoints(arrivalTimes: List<List<ArrivalTimeModel>?>) {
        val updatedList = arrivalTimesData.value?.map { stopPoint ->
            arrivalTimes.forEach {
                if (it != null && it.isNotEmpty() && it[0].naptanId == stopPoint.id) {
                    return@map stopPoint.copy(arrivalsTimes = it.sortedBy { it.timeToStation }.take(3))
                }
            }
            stopPoint
        }
        arrivalTimesData.postValue(updatedList)
    }

    /**
     * handle the error code that come from the server
     */
    private fun handleError(result: NetworkHttpError) {
        stopPointsNetworkHttpError.postValue(result)
    }

    /**
     * clear the resources when the VM cleared
     */
    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        if (!arrivalsTimeDisposable.isDisposed) {
            arrivalsTimeDisposable.dispose()
        }
        super.onCleared()
    }

}