package com.ukstations.app.presentation.views.linedetails.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ukstations.app.R
import com.ukstations.app.presentation.models.StopPointSequence
import com.ukstations.app.presentation.views.linedetails.viewholders.StopPointSequenceViewHolder

class StopPointSequenceAdapter(private val lineStopPoints: List<StopPointSequence>) : RecyclerView.Adapter<StopPointSequenceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): StopPointSequenceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StopPointSequenceViewHolder(layoutInflater.inflate(R.layout.stop_point_sequence_item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return lineStopPoints.size
    }

    override fun onBindViewHolder(holder: StopPointSequenceViewHolder, position: Int) {
        val stopPoint = lineStopPoints[holder.adapterPosition]
        when {
            holder.adapterPosition == 0 -> {
                stopPoint.hideTop = true
                stopPoint.hideCenter = false
                stopPoint.hideBottom = false
            }
            holder.adapterPosition == lineStopPoints.size - 1 -> {
                stopPoint.hideTop = false
                stopPoint.hideCenter = false
                stopPoint.hideBottom = true
            }
            else -> {
                stopPoint.hideTop = false
                stopPoint.hideCenter = false
                stopPoint.hideBottom = false
            }
        }
        holder.bind(stopPoint)
    }
}