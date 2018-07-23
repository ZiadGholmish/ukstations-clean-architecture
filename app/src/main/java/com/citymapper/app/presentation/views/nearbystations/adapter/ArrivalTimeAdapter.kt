package com.citymapper.app.presentation.views.nearbystations.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.presentation.views.nearbystations.viewholder.ArrivalTimeViewHolder

class ArrivalTimeAdapter(val context: Context) : RecyclerView.Adapter<ArrivalTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArrivalTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArrivalTimeViewHolder(layoutInflater.inflate(R.layout.arrival_time_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return 4//stopPointArrivalTimes.size
    }

    override fun onBindViewHolder(holder: ArrivalTimeViewHolder, position: Int) {
    }


}