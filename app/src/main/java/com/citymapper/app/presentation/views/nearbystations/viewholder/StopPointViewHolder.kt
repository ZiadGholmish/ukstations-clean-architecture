package com.citymapper.app.presentation.views.nearbystations.viewholder

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.citymapper.app.R
import com.citymapper.app.presentation.views.nearbystations.adapter.ArrivalTimeAdapter

class StopPointViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    private val imgTubeIcon: ImageView = view.findViewById(R.id.imgTubeIcon)
    private val tvStationName: TextView = view.findViewById(R.id.tvStationName)
    private val tvMinutes: TextView = view.findViewById(R.id.tvMinutes)
    private val stopPointArrivalsRecycler: RecyclerView = view.findViewById(R.id.stopPointArrivalsRecycler)

    init {
        setupRecyclerView()
    }

    fun bind() {
        val arrivalTimeAdapter = ArrivalTimeAdapter(context)
        stopPointArrivalsRecycler.adapter = arrivalTimeAdapter
    }

    private fun setupRecyclerView() {
        val linearLayout = LinearLayoutManager(context)
        linearLayout.orientation = LinearLayoutManager.VERTICAL
        stopPointArrivalsRecycler.layoutManager = linearLayout
    }

}