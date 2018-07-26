package com.citymapper.app.domain.models.stoppoint

import com.citymapper.app.domain.models.arrivals.ArrivalTimeModel

data class StopPointModel(val id: String, var commonName: String?, val distance: Double,
                          val lat: Double, val lon: Double, var arrivalsTimes: List<ArrivalTimeModel>)