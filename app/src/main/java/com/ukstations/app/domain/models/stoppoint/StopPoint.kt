package com.ukstations.app.domain.models.stoppoint

import com.ukstations.app.domain.models.arrivals.ArrivalTime

data class StopPoint(val id: String, var commonName: String?, val distance: Double,
                     val lat: Double, val lon: Double, var arrivalsTimes: List<ArrivalTime>)