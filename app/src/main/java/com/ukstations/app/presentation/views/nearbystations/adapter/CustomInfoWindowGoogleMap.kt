package com.ukstations.app.presentation.views.nearbystations.adapter

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.ukstations.app.R
import com.ukstations.app.domain.models.stoppoint.StopPoint
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowGoogleMap(private val context: AppCompatActivity) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View? {
        return null
    }

    override fun getInfoWindow(marker: Marker): View {
        val infoView = context.layoutInflater.inflate(R.layout.stop_point_arrivals_info_windwo_layout, null)
        showMarkerInfo(marker, infoView)
        return infoView
    }


    private fun showMarkerInfo(marker: Marker, infoView: View) {

        //get the object from the tag
        val stopPoint: StopPoint = marker.tag as StopPoint

        val tvStationName: TextView = infoView.findViewById(R.id.tvStationName)
        val tvMeter: TextView = infoView.findViewById(R.id.tvMeter)

        tvStationName.text = stopPoint.commonName?.trim()
        tvMeter.text = stopPoint.distance.toInt().toString()

    }

}