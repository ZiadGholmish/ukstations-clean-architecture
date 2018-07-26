package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.domain.models.linedetails.LineDetailsNetworkHttpError
import com.citymapper.app.domain.models.linedetails.LineDetailsPayLoad
import com.citymapper.app.domain.models.linedetails.LineDetailsResult
import com.citymapper.app.domain.usecase.FetchLineDetailsUseCase
import com.citymapper.app.presentation.models.StopPointSequenceParcelable
import com.citymapper.app.util.toParcelable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LineDetailsVM @Inject constructor(private val fetchLineDetailsUseCase: FetchLineDetailsUseCase) : ViewModel() {

    //disposal to clear the old requests and clear resources then the vm is cleared
    private val compositeDisposable = CompositeDisposable()

    //live data to control the values
    val lineStopPointsLiveData = MutableLiveData<List<StopPointSequenceParcelable>>()
    val lineStopPointsNetworkHttpError = MutableLiveData<LineDetailsNetworkHttpError>()
    val lineStopPointsRequestState = MutableLiveData<RequestState>()


    fun fetchLineDetails(id: String, direction: String) {
        compositeDisposable.clear()
        val fetchLineDetailsObservable = fetchLineDetailsUseCase.fetchStopPointArrivals(id, direction)
                .doOnError { lineStopPointsRequestState.value = RequestState.Complete }
                .doOnNext { lineStopPointsRequestState.value = RequestState.Complete }
                .doOnSubscribe { lineStopPointsRequestState.value = RequestState.Loading }
                .subscribe({ response ->
                    handleNetworkResult(response)
                }, { error ->
                    lineStopPointsRequestState.postValue(RequestState.Complete)
                    error.printStackTrace()
                })
        compositeDisposable.add(fetchLineDetailsObservable)
    }

    /**
     * handle the response from login or check the error code
     */
    private fun handleNetworkResult(result: LineDetailsResult) {
        return when (result) {
            is LineDetailsPayLoad -> handlePayLoad(result)
            is LineDetailsNetworkHttpError -> handleError(result)
        }
    }


    /**
     * handle the success result
     */
    private fun handlePayLoad(payload: LineDetailsPayLoad) {
        when (payload) {
            is LineDetailsPayLoad.Data ->
                if (payload.data.stopPointSequences.isNotEmpty()) {
                    lineStopPointsLiveData.value = payload.data.stopPointSequences[0].stopPoint.map { it.toParcelable() }
                }
        }
    }

    /**
     * handle the error code that come from the server
     */
    private fun handleError(error: LineDetailsNetworkHttpError) {
        lineStopPointsNetworkHttpError.postValue(error)
    }


    /**
     * clear the resources when the VM cleared
     */
    override fun onCleared() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onCleared()
    }

}