package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.Observer
import com.citymapper.app.app.AbsPresenter
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.domain.models.linedetails.LineDetailsNetworkHttpError
import com.citymapper.app.presentation.models.ArrivalTimeParcelable
import javax.inject.Inject

class LineDetailsPresenter @Inject constructor() : AbsPresenter<LineDetailsController>() {

    private lateinit var liveDetailsVM: LineDetailsVM

    fun initPresenter(liveDetailsVM: LineDetailsVM, arrivalTime: ArrivalTimeParcelable?) {
        this.liveDetailsVM = liveDetailsVM
        setObservers()
        arrivalTime?.let {
            liveDetailsVM.fetchLineDetails(arrivalTime.id, arrivalTime.direction?.let { it }
                    ?: "inbound")
            mView?.showLineName(arrivalTime.lineName)
        }
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