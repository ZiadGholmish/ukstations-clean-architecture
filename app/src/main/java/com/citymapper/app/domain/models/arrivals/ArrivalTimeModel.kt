package com.citymapper.app.domain.models.arrivals

data class ArrivalTimeModel(val id: String, val naptanId: String,
                            val lineName: String, var destinationName: String?, val timeToStation: Int,
                            val expectedArrival: String)