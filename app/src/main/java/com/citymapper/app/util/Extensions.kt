package com.citymapper.app.util

import com.citymapper.app.data.remote.models.stops.StopPoint
import com.citymapper.app.presentation.views.nearbystations.StopPointClusterMapItem
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.Cluster


fun StopPoint.toClusterItem(): StopPointClusterMapItem {
    val latLong = LatLng(lat, lon)
    return StopPointClusterMapItem(latLong, commonName, placeType)
}