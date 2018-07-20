package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.data.remote.models.stops.StopPoint

interface NearbyStationsController : LifecycleOwner {

    fun showStopPoints(stopPoints: List<StopPoint>)

    fun zoomToStations(stopPoint: StopPoint?)

    fun showNoPointsAvailable()

    fun showFetchingError(errorMessage: String)

    fun showMessage(resId: Int)

    fun showLoading()

    fun hideLoading()


}