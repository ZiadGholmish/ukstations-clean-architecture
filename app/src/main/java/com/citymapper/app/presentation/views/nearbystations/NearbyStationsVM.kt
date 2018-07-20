package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.data.remote.models.stops.NetworkHttpError
import com.citymapper.app.data.remote.models.stops.StopPoint
import com.citymapper.app.data.remote.models.stops.StopPointsNetworkResult
import com.citymapper.app.data.remote.models.stops.StopPointsPayload
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import javax.inject.Inject

class NearbyStationsVM @Inject constructor(private val fetchStopPointsUseCase: FetchStopPointsUseCase) : ViewModel() {

    //points
    val stopPointsLiveData = MutableLiveData<List<StopPoint>>()

    //to listen for the errors in the network
    val stopPointsNetworkHttpError = MutableLiveData<NetworkHttpError>()

    //to listen for the order update status
    val stopPointsRequestState = MutableLiveData<RequestState>()


    fun loadStopPointsTest() {
        fetchStopPointsUseCase
                .fetchStopPoints("NaptanRailStation", 1000, 51.583157, -0.074757)
                .doOnError { stopPointsRequestState.value = RequestState.Complete }
                .doOnNext { stopPointsRequestState.value = RequestState.Complete }
                .doOnSubscribe { stopPointsRequestState.value = RequestState.Loading }
                .subscribe({ response ->
                    handleNetworkResult(response)
                }, { error ->
                    stopPointsRequestState.value = RequestState.Complete
                    error.printStackTrace()
                })
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
                } else stopPointsNetworkHttpError.value = NetworkHttpError.UnknownError(500, "Error from the api")
            }
        }
    }

    /**
     * handle the error code that come from the server
     */
    private fun handleError(result: NetworkHttpError) {
        stopPointsNetworkHttpError.value = result
    }

}