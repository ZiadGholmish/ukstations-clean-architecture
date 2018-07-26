package com.citymapper.app.domain.models.arrivals

data class ArrivalTime(val id: String,
                       val naptanId: String,
                       val lineName: String,
                       val lineId: String,
                       var destinationName: String?,
                       val timeToStation: Int,
                       val expectedArrival: String,
                       val towards: String,
                       var direction: String?,
                       var platformName: String)