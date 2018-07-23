package com.citymapper.app.presentation.views.nearbystations.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeModel

class ArrivalTimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imgTubeIcon: ImageView = view.findViewById(R.id.imgTubeIcon)
    val tvArrivalTime: TextView = view.findViewById(R.id.tvArrivalTime)
    val tvStreetName: TextView = view.findViewById(R.id.tvStreetName)
    val tvArrivalTimeInMinute: TextView = view.findViewById(R.id.tvArrivalTimeInMinute)
    val tvPlatNumber: TextView = view.findViewById(R.id.tvPlatNumber)

    fun bind(arrivalTimeModel: ArrivalTimeModel) {

    }

}