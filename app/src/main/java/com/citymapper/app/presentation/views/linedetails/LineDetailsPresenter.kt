package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.Observer
import com.citymapper.app.app.AbsPresenter
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.domain.models.linedetails.LineDetailsNetworkHttpError
import com.citymapper.app.presentation.models.ArrivalTimeParcelable
import com.citymapper.app.presentation.models.StopPointSequenceParcelable
import kotlinx.android.synthetic.main.activity_line_details.*
import javax.inject.Inject

class LineDetailsPresenter @Inject constructor() : AbsPresenter<LineDetailsController>() {

    private lateinit var liveDetailsVM: LineDetailsVM

    private var selectedArrivalTime: ArrivalTimeParcelable? = null

    fun initPresenter(liveDetailsVM: LineDetailsVM, arrivalTime: ArrivalTimeParcelable?) {
        this.liveDetailsVM = liveDetailsVM
        this.selectedArrivalTime = arrivalTime
        setObservers()
        selectedArrivalTime?.let {
            liveDetailsVM.fetchLineDetails(it.lineId, it.direction?.let { it } ?: "inbound")
            mView?.showLineName(it.lineName)
        }
    }

    private fun setObservers() {

        liveDetailsVM.lineStopPointsLiveData.observe(mView!!, Observer {
            it?.let { showLinePoints(it) }
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

    private fun showLinePoints(stopPoints: List<StopPointSequenceParcelable>) {
        val updatedStopPoints = stopPoints.map {
            if (it.id == selectedArrivalTime!!.naptanId) {
                it.clickedStation = true
            }
            it
        }
        mView?.showSequenceStopPoints(updatedStopPoints)
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