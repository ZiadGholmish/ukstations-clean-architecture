package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.domain.models.stoppoint.StopPoint

interface LineDetailsController : LifecycleOwner {

    fun showSequenceStopPoints(stopPoint: List<StopPoint>)

    fun showNoStopPoints()

    fun showFetchingError(errorMessage: String)

    fun showLoading()

    fun hideLoading()

    fun showLineName(lineName: String)

}