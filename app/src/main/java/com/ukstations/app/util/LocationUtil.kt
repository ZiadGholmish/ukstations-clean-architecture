package com.ukstations.app.util

import com.google.android.gms.maps.model.LatLng

object LocationUtil {

    const val defaultLat = 51.510479
    const val defaultLon = -0.131495

    private val latRange = 49.9..58.7
    private val lonRange = -11.05..1.78

    /**
     * function to check if the location inside uk or not
     */
    fun checkLocationInsideCountry(markerLocation: LatLng): Boolean {
        return latRange.contains(markerLocation.latitude) &&
                lonRange.contains(markerLocation.longitude)
    }

}