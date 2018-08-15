package com.ukstations.app.presentation.views.nearbystations.viewholder

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ukstations.app.R
import com.ukstations.app.domain.models.stoppoint.StopPoint
import com.ukstations.app.presentation.views.nearbystations.ArrivalTimeClickListner
import com.ukstations.app.presentation.views.nearbystations.adapter.ArrivalTimeAdapter

class StopPointViewHolder(view: View, val context: Context, private val onArrivalTimeClickListener: ArrivalTimeClickListner) : RecyclerView.ViewHolder(view) {

    private val imgTubeIcon: ImageView = view.findViewById(R.id.imgTubeIcon)
    private val tvStationName: TextView = view.findViewById(R.id.tvStationName)
    private val tvMeter: TextView = view.findViewById(R.id.tvMeter)
    private val stopPointArrivalsRecycler: RecyclerView = view.findViewById(R.id.stopPointArrivalsRecycler)

    init {
        setupRecyclerView()
    }

    fun bind(stopPoint: StopPoint) {
        tvStationName.text = stopPoint.commonName
        tvMeter.text = "${stopPoint.distance.toInt()} Meter"
        val arrivalTimeAdapter = ArrivalTimeAdapter(stopPoint.arrivalsTimes, context, onArrivalTimeClickListener)
        stopPointArrivalsRecycler.adapter = arrivalTimeAdapter
    }

    private fun setupRecyclerView() {
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        stopPointArrivalsRecycler.layoutManager = linearLayout
    }

}