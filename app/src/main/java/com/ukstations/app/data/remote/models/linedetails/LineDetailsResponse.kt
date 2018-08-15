package com.ukstations.app.data.remote.models.linedetails


data class LineDetailsResponseModel(
        val lineId: String,
        val lineName: String,
        val direction: String,
        val stopPointSequences: List<LineSequenceResponse>)

data class LineSequenceResponse(
        val lineId: String,
        val lineName: String,
        val direction: String,
        val branchId: Int,
        val stopPoint: List<LineStopPoint>)

data class LineStopPoint(
        val parentId: String,
        val stationId: String,
        val modes: List<String>,
        val stopType: String,
        val id: String,
        val name: String,
        val lat: Double,
        val lon: Double,
        val hasDisruption: Boolean
)

