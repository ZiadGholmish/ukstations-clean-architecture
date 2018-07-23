package com.citymapper.app.presentation.views.nearbystations.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.domain.models.StopPointArrivalTimesModel

class StopPointViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val imgTubeIcon: ImageView = view.findViewById(R.id.imgTubeIcon)
    val tvStationName: TextView = view.findViewById(R.id.tvStationName)
    val tvMins: TextView = view.findViewById(R.id.tvMins)
    val stopPointArrivalsRecycler: RecyclerView = view.findViewById(R.id.stopPointArrivalsRecycler)

    fun bind(stopPointArrivalTimes: StopPointArrivalTimesModel) {

    }

}