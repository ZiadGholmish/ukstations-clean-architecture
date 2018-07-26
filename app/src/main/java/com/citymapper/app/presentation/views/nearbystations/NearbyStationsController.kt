package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.google.android.gms.maps.model.LatLng

interface NearbyStationsController : LifecycleOwner {

    fun showStopPoints(stopPoints: List<StopPoint>)

    fun showArrivalTimes(stopPoints: List<StopPoint>)

    fun zoomToStations(stopPoint: StopPoint?)

    fun moveMapToDefaultLocation(defaultLatLng: LatLng)

    fun showNoPointsAvailable()

    fun showFetchingError(errorMessage: String)

    fun showMessage(resId: Int)

    fun showLoading()

    fun hideLoading()


}