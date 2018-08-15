package com.ukstations.app.presentation.views.linedetails

import android.arch.lifecycle.LifecycleOwner
import com.ukstations.app.presentation.models.StopPointSequence

interface LineDetailsController : LifecycleOwner {

    fun showSequenceStopPoints(stopPoint: List<StopPointSequence>)

    fun showNoStopPoints()

    fun showFetchingError(errorMessage: String)

    fun showLoading()

    fun hideLoading()

    fun showLineName(lineName: String)

}