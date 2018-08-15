package com.ukstations.app.data.remote.models.stops


data class StopPointsResponseModel(
        val centrePoint: List<Double>,
        val stopPoints: List<StopPointResponse>,
        val pageSize: Int,
        val total: Int,
        val page: Int,
        val message: String,
        val httpStatusCode: Int
)

data class StopPointResponse(
        val naptanId: String,
        val modes: List<String>,
        val icsCode: String,
        val stopType: String,
        val stationNaptan: String,
        val status: Boolean,
        val id: String,
        val commonName: String,
        val distance: Double,
        val placeType: String,
        val lat: Double,
        val lon: Double
)


