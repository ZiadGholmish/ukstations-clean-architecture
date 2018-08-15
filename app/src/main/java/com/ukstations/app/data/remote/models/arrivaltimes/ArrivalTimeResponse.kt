package com.ukstations.app.data.remote.models.arrivaltimes

data class ArrivalTimeResponse(
        val id: String,
        val operationType: Int,
        val vehicleId: String,
        val naptanId: String,
        val stationName: String,
        val lineId: String,
        val lineName: String,
        val platformName: String,
        val direction: String,
        val bearing: String,
        val destinationNaptanId: String,
        val destinationName: String,
        val timestamp: String,
        val timeToStation: Int,
        val currentLocation: String,
        val towards: String,
        val expectedArrival: String,
        val timeToLive: String,
        val modeName: String
)


