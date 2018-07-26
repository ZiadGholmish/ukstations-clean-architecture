package com.citymapper.app.presentation.views.nearbystations.adapter

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.citymapper.app.R
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.presentation.views.nearbystations.ArrivalTimeClickListner
import com.citymapper.app.presentation.views.nearbystations.viewholder.StopPointViewHolder
import com.citymapper.app.util.StopPointDiffCallback

class StopPointAdapter(private val stopPoints: MutableList<StopPoint>, val context: Context, private val onArrivalTimeClickListener: ArrivalTimeClickListner) : RecyclerView.Adapter<StopPointViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StopPointViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StopPointViewHolder(layoutInflater.inflate(R.layout.stop_point_arrivals_times_layout, parent, false), context, onArrivalTimeClickListener)
    }

    override fun getItemCount(): Int {
        return stopPoints.size
    }

    override fun onBindViewHolder(holder: StopPointViewHolder, position: Int) {
        holder.bind(stopPoints[holder.adapterPosition])
    }

    fun setNewStopPoints(newStopPoints: List<StopPoint>) {
        val diffCallback = StopPointDiffCallback(this.stopPoints, newStopPoints)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        stopPoints.clear()
        stopPoints.addAll(newStopPoints)
        diffResult.dispatchUpdatesTo(this)
    }
}