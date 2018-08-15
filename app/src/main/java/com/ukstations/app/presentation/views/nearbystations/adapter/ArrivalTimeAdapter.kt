package com.ukstations.app.presentation.views.nearbystations.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ukstations.app.R
import com.ukstations.app.domain.models.arrivals.ArrivalTime
import com.ukstations.app.presentation.views.nearbystations.ArrivalTimeClickListner
import com.ukstations.app.presentation.views.nearbystations.viewholder.ArrivalTimeViewHolder

class ArrivalTimeAdapter(private val arrivalTimes: List<ArrivalTime>, val context: Context, private val onArrivalTimeClickListener: ArrivalTimeClickListner) : RecyclerView.Adapter<ArrivalTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArrivalTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArrivalTimeViewHolder(layoutInflater.inflate(R.layout.arrival_time_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return arrivalTimes.size
    }

    override fun onBindViewHolder(holder: ArrivalTimeViewHolder, position: Int) {
        val arrivalTime = arrivalTimes[holder.adapterPosition]
        holder.bind(arrivalTime)
        holder.itemView.setOnClickListener { onArrivalTimeClickListener.onArrivalClick(arrivalTime) }
    }

}