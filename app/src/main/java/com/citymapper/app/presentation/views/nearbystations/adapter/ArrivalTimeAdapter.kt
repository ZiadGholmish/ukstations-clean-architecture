package com.citymapper.app.presentation.views.nearbystations.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.app.AppConstants
import com.citymapper.app.domain.models.arrivals.ArrivalTime
import com.citymapper.app.presentation.views.linedetails.LineDetailsActivity
import com.citymapper.app.presentation.views.nearbystations.viewholder.ArrivalTimeViewHolder
import com.citymapper.app.util.toParcelable

class ArrivalTimeAdapter(private val arrivalTimes: List<ArrivalTime>, val context: Context) : RecyclerView.Adapter<ArrivalTimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArrivalTimeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ArrivalTimeViewHolder(layoutInflater.inflate(R.layout.arrival_time_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return arrivalTimes.size
    }

    override fun onBindViewHolder(holder: ArrivalTimeViewHolder, position: Int) {
        holder.bind(arrivalTimes[holder.adapterPosition])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, LineDetailsActivity::class.java)
            intent.putExtra(AppConstants.ARRIVAL_TIME_INTENT_NAME, arrivalTimes[holder.adapterPosition].toParcelable())
            context.startActivity(intent)
        }
    }

}