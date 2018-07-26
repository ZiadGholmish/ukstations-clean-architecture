package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.presentation.models.StopPointSequence

interface LineDetailsController : LifecycleOwner {

    fun showSequenceStopPoints(stopPoint: List<StopPointSequence>)

    fun showNoStopPoints()

    fun showFetchingError(errorMessage: String)

    fun showLoading()

    fun hideLoading()

    fun showLineName(lineName: String)

}