package com.citymapper.app.data.remote.models.stops


data class StopPointsResponseModel(
        val centrePoint: List<Double>,
        val stopPoints: List<StopPointEntity>,
        val pageSize: Int,
        val total: Int,
        val page: Int,
        val message: String,
        val httpStatusCode: Int

)

data class StopPointEntity(
        val naptanId: String,
        val modes: List<String>,
        val icsCode: String,
        val stopType: String,
        val stationNaptan: String,
        val hubNaptanCode: String,
        val lines: List<Line>,
        val lineGroup: List<LineGroup>,
        val lineModeGroups: List<LineModeGroup>,
        val status: Boolean,
        val id: String,
        val commonName: String,
        val distance: Double,
        val placeType: String,
        val additionalProperties: List<AdditionalProperty>,
        val lat: Double,
        val lon: Double
)

data class AdditionalProperty(
        val category: String,
        val key: String,
        val sourceSystemKey: String,
        val value: String
)

data class LineModeGroup(
        val modeName: String,
        val lineIdentifier: List<String>
)

data class Line(
        val id: String,
        val name: String,
        val uri: String,
        val type: String
)


data class LineGroup(
        val naptanIdReference: String,
        val stationAtcoCode: String,
        val lineIdentifier: List<String>
)