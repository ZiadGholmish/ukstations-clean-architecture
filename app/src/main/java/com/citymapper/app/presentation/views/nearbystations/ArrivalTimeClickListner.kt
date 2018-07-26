package com.citymapper.app.presentation.views.nearbystations

import com.citymapper.app.domain.models.arrivals.ArrivalTime

interface ArrivalTimeClickListner {

    fun onArrivalClick(arrivalTime: ArrivalTime)
}