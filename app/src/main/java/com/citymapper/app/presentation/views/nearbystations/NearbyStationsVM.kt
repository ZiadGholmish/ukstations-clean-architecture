package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.data.remote.models.stops.StopPoint
import com.citymapper.app.domain.models.NetworkHttpError
import com.citymapper.app.domain.models.StopPintsPayLoad
import com.citymapper.app.domain.models.StopPointsResult
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(private val fetchStopPointsUseCase: FetchStopPointsUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

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
                } else stopPointsRequestState.value = RequestState.Complete
            }
        }
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