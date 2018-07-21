package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.data.remote.models.stops.NetworkHttpError
import com.citymapper.app.data.remote.models.stops.StopPoint
import com.citymapper.app.data.remote.models.stops.StopPointsNetworkResult
import com.citymapper.app.data.remote.models.stops.StopPointsPayload
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(private val fetchStopPointsUseCase: FetchStopPointsUseCase) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    //points
    val stopPointsLiveData = MutableLiveData<List<StopPoint>>()

    //to listen for the errors in the network
    val stopPointsNetworkHttpError = MutableLiveData<NetworkHttpError>()

    //to listen for the order update status
    val stopPointsRequestState = MutableLiveData<RequestState>()

    private val stopTypes = listOf("NaptanMetroStation","NaptanMetroPlatform" , "NaptanMetroEntrance" , "NaptanMetroAccessArea")

    fun loadStopPointsTest(lat: Double, lon: Double) {
        compositeDisposable.add(
                fetchStopPointsUseCase
                        .fetchStopPoints(stopTypes.joinToString(), 1000, lat, lon)
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
    private fun handleNetworkResult(result: StopPointsNetworkResult) {
        return when (result) {
            is StopPointsPayload -> handleResult(result)
            is NetworkHttpError -> handleError(result)
        }
    }


    /**
     * handle the success result and check the envelop for messages or what
     */
    private fun handleResult(result: StopPointsPayload) {
        when (result) {
            is StopPointsPayload.StopPointsModelsSealed -> {
                if (!result.stopPoints.isEmpty()) {
                    stopPointsLiveData.value = result.stopPoints
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