package com.citymapper.app.presentation.views.nearbystations

import android.arch.lifecycle.LifecycleOwner
import com.citymapper.app.domain.models.stoppoint.StopPointModel
import com.google.android.gms.maps.model.LatLng

interface NearbyStationsController : LifecycleOwner {

    fun showStopPoints(stopPointModels: List<StopPointModel>)

    fun showArrivalTimes(stopPointModels: List<StopPointModel>)

    fun zoomToStations(stopPointModel: StopPointModel?)

    fun moveMapToDefaultLocation(defaultLatLng: LatLng)

    fun showNoPointsAvailable()

    fun showFetchingError(errorMessage: String)

    fun showMessage(resId: Int)

    fun showLoading()

    fun hideLoading()


}