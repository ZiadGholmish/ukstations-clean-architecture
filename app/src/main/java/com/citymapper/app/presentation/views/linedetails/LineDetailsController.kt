package com.citymapper.app.presentation.views.linedetails

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.domain.models.stoppoint.StopPointModel

interface LineDetailsController : LifecycleOwner {

    fun showSequenceStopPoints(stopPointModel: List<StopPointModel>)

    fun showNoStopPoints()

    fun showFetchingError(errorMessage: String)

    fun showLoading()

    fun hideLoading()

}