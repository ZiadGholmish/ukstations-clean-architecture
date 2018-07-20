package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.data.remote.models.stops.StopPoint

interface NearbyStationsController : LifecycleOwner {


    fun showLoading()

    fun hideLoading()

    fun showStopPoints(stopPoints: List<StopPoint>)

}