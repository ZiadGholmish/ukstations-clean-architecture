package com.citymapper.app.domain.models.arrivals

import java.util.*

data class ArrivalTimeModel(val id: String,
                            val naptanId: String,
                            val lineName: String,
                            var destinationName: String?,
                            val timeToStation: Int,
                            val expectedArrival: String,
                            val towards: String,
                            var direction: String?)