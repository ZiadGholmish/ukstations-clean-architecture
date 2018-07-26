package com.citymapper.app.presentation.views.linedetails.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.presentation.views.linedetails.LineDetailsActivity
import com.citymapper.app.presentation.views.linedetails.viewholders.StopPointSequenceViewHolder
import com.citymapper.app.presentation.views.nearbystations.viewholder.ArrivalTimeViewHolder

class StopPointSequenceAdapter : RecyclerView.Adapter<StopPointSequenceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StopPointSequenceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StopPointSequenceViewHolder(layoutInflater.inflate(R.layout.stop_point_sequence_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return 30
    }

    override fun onBindViewHolder(holder: StopPointSequenceViewHolder, position: Int) {

        when {
            holder.adapterPosition == 0 -> holder.bind(true, true, false)
            holder.adapterPosition == 29 -> holder.bind(false, false, true)
            else -> holder.bind(false, false, false)
        }


    }
}