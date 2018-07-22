package com.citymapper.app.presentation.views.nearbystations

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeModel
import com.citymapper.app.domain.models.StopPointArrivalTimes

class LiveArrivalTimeAdapter(private val stopPointArrivalTimes: List<StopPointArrivalTimes>, val context: Context) : RecyclerView.Adapter<ArrivalTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArrivalTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArrivalTimeViewHolder(layoutInflater.inflate(R.layout.stop_point_arrivals_times_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return stopPointArrivalTimes.size
    }

    override fun onBindViewHolder(holder: ArrivalTimeViewHolder, position: Int) {
    }
}