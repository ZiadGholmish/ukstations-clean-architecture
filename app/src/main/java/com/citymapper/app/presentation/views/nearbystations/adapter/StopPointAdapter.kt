package com.citymapper.app.presentation.views.nearbystations.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeModel
import com.citymapper.app.presentation.views.nearbystations.viewholder.StopPointViewHolder

class StopPointAdapter(private val arrivalTimesModel: List<ArrivalTimeModel>, val context: Context) : RecyclerView.Adapter<StopPointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StopPointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StopPointViewHolder(layoutInflater.inflate(R.layout.stop_point_arrivals_times_layout, parent, false), context)
    }

    override fun getItemCount(): Int {
        return 50 //stopPointArrivalTimes.size
    }

    override fun onBindViewHolder(holder: StopPointViewHolder, position: Int) {
        holder.bind()
    }
}