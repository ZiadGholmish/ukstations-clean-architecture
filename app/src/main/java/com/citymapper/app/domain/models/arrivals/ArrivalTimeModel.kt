package com.citymapper.app.domain.models.arrivals

data class ArrivalTimeModel(val lineName: String, val destinationName: String, val timeToStation: Int, val expectedArrival: String)