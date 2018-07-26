package com.citymapper.app.data.remote.models.linedetails


data class LineDetailsResponseModel(
        val lineId: String,
        val lineName: String,
        val direction: String,
        val isOutboundOnly: Boolean,
        val mode: String,
        val lineStrings: List<String>,
        val stations: List<Station>,
        val stopPointSequences: List<StopPointSequence>,
        val orderedLineRoutes: List<OrderedLineRoute>
)

data class OrderedLineRoute(
        val name: String,
        val naptanIds: List<String>,
        val serviceType: String
)

data class StopPointSequence(
        val lineId: String,
        val lineName: String,
        val direction: String,
        val branchId: Int,
        val stopPoint: List<LineStopPoint>,
        val serviceType: String
)

data class LineStopPoint(
        val parentId: String,
        val stationId: String,
        val icsId: String,
        val topMostParentId: String,
        val modes: List<String>,
        val stopType: String,
        val zone: String,
        val lines: List<Line>,
        val status: Boolean,
        val id: String,
        val name: String,
        val lat: Double,
        val lon: Double,
        val hasDisruption: Boolean
)

data class Line(
        val id: String,
        val name: String,
        val uri: String,
        val type: String
)

data class Station(
        val stationId: String,
        val icsId: String,
        val topMostParentId: String,
        val modes: List<String>,
        val stopType: String,
        val zone: String,
        val lines: List<Line>,
        val status: Boolean,
        val id: String,
        val name: String,
        val lat: Double,
        val lon: Double
)