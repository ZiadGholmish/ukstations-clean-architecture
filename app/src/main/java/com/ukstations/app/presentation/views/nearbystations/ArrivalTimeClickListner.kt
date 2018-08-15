package com.ukstations.app.presentation.views.nearbystations

import com.ukstations.app.domain.models.arrivals.ArrivalTime

interface ArrivalTimeClickListner {

    fun onArrivalClick(arrivalTime: ArrivalTime)
}