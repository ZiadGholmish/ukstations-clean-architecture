package com.citymapper.app.presentation.views.nearbystations.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.domain.models.arrivals.ArrivalTime

class ArrivalTimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imgTubeIcon: ImageView = view.findViewById(R.id.imgTubeIcon)
    val tvArrivalTime: TextView = view.findViewById(R.id.tvArrivalTime)
    val tvStreetName: TextView = view.findViewById(R.id.tvStreetName)
    val tvArrivalTimeInMinute: TextView = view.findViewById(R.id.tvArrivalTimeInMinute)
    val tvPlatNumber: TextView = view.findViewById(R.id.tvPlatNumber)

    fun bind(arrivalTimeModel: ArrivalTime) {
        tvArrivalTime.text = arrivalTimeModel.expectedArrival
        tvStreetName.text = "${arrivalTimeModel.direction.let { it }} - ${arrivalTimeModel.towards}"
        tvArrivalTimeInMinute.text = "${(arrivalTimeModel.timeToStation / 60)} min"
        tvPlatNumber.text = getPlatformNumber(arrivalTimeModel.platformName)

    }

    private fun getPlatformNumber(platform: String): String {
        val platformParts = platform.split("Platform")
        if (platformParts.size > 1) {
            return "Plat. ${platformParts[1].trim()}"
        }
        return platform
    }

}