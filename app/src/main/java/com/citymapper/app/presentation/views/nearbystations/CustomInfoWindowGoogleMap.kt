package com.citymapper.app.presentation.views.nearbystations

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.data.remote.models.stops.StopPoint
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowGoogleMap(private val context: AppCompatActivity) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View {
        val infoView = context.layoutInflater.inflate(R.layout.stop_point_arrivals_times_layout, null)
        showMarkerInfo(marker, infoView)
        return infoView
    }


    private fun showMarkerInfo(marker: Marker, infoView: View) {

        //get the object from the tag
        val stopPoint: StopPoint = marker.tag as StopPoint


        val tvStationName: TextView = infoView.findViewById(R.id.tvStationName)
        val tvMins: TextView = infoView.findViewById(R.id.tvMins)

        tvStationName.text = stopPoint.commonName.trim()
        tvMins.text = stopPoint.stopType

    }

}