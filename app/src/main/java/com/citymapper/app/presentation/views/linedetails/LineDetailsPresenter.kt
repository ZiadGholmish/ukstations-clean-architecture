package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.Observer
import com.citymapper.app.app.AbsPresenter
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.domain.models.linedetails.LineDetailsNetworkHttpError
import javax.inject.Inject

class LineDetailsPresenter @Inject constructor() : AbsPresenter<LineDetailsController>() {

    private lateinit var liveDetailsVM: LineDetailsVM

    fun initPresenter(liveDetailsVM: LineDetailsVM, id: String, direction: String) {
        this.liveDetailsVM = liveDetailsVM
        setObservers()
        liveDetailsVM.fetchLineDetails(id, direction)
    }

    private fun setObservers() {

        liveDetailsVM.lineStopPointsLiveData.observe(mView!!, Observer {
            mView?.showSequenceStopPoints(it.let { it } ?: listOf())
        })

        liveDetailsVM.lineStopPointsNetworkHttpError.observe(mView!!, Observer {
            handleError(it!!)
        })

        liveDetailsVM.lineStopPointsRequestState.observe(mView!!, Observer {
            when (it!!) {
                is RequestState.Complete -> mView?.hideLoading()
                is RequestState.Idle -> mView?.hideLoading()
                is RequestState.Loading -> mView?.showLoading()
            }
        })
    }

    /**
     * handle the error code that come from the server
     */
    private fun handleError(result: LineDetailsNetworkHttpError) {
        when (result) {
            is LineDetailsNetworkHttpError.Error -> mView?.showFetchingError(result.message)
        }
    }

}