package com.ukstations.app.data.datautil

import com.ukstations.app.data.remote.models.arrivaltimes.ArrivalTimeResponse
import com.ukstations.app.data.remote.models.linedetails.LineDetailsResponseModel
import com.ukstations.app.data.remote.models.linedetails.LineStopPoint
import com.ukstations.app.data.remote.models.linedetails.LineSequenceResponse
import com.ukstations.app.data.remote.models.stops.StopPointResponse
import com.ukstations.app.data.remote.models.stops.StopPointsResponseModel
import com.ukstations.app.domain.models.arrivals.ArrivalTime
import com.ukstations.app.domain.models.arrivals.StopArrivalsPayLoad
import com.ukstations.app.domain.models.linedetails.LineDetails
import com.ukstations.app.domain.models.linedetails.LineDetailsPayLoad
import com.ukstations.app.domain.models.linedetails.LineSequence
import com.ukstations.app.domain.models.stoppoint.StopPintsPayLoad
import com.ukstations.app.domain.models.stoppoint.StopPoint
import retrofit2.Response


/**
 * extension function to format the response and transfer to sealed classes
 */
fun Response<StopPointsResponseModel>.toAggregatePointsResult() =
        if (this.isSuccessful) {
            StopPintsPayLoad.Data(this.body()!!.stopPoints.map { it.toStopPoint() })
        } else {
            Util.fromErrorResponse(this.code(), Util.formatErrorBody(this.errorBody()))
        }

fun Response<List<ArrivalTimeResponse>>.toAggregateArrivalsResult() =
        if (this.isSuccessful) {
            StopArrivalsPayLoad.Data(this.body()!!.map { it.toArrivalTime() })
        } else {
            Util.fromArrivalErrorResponse(this.code(), Util.formatErrorBody(this.errorBody()))
        }

fun Response<LineDetailsResponseModel>.toAggregateLineDetailsResult() =
        if (this.isSuccessful) {
            LineDetailsPayLoad.Data(this.body()!!.toLineDetailsModel())
        } else {
            Util.fromLineDetailsErrorResponse(this.code(), Util.formatErrorBody(this.errorBody()))
        }

/**
 * extension function to map the entity to the stop point domain model
 */
fun StopPointResponse.toStopPoint(): StopPoint {
    return StopPoint(this.id, this.commonName, this.distance, this.lat, this.lon, listOf())
}


/**
 * extension function to map the entity to the stop point domain model
 */
fun LineStopPoint.toStopPoint(): StopPoint {
    return StopPoint(this.id, this.name, 0.0, this.lat, this.lon, listOf())
}


fun ArrivalTimeResponse.toArrivalTime(): ArrivalTime {
    return ArrivalTime(this.id, this.naptanId, this.lineName, this.lineId,
            this.destinationName, this.timeToStation,
            this.expectedArrival, this.towards, this.direction,
            this.platformName)
}

/**
 * extension function to map the data sequence model to the domain one
 */
fun LineSequenceResponse.toStopPointSequenceModel(): LineSequence {
    return LineSequence(lineId, lineName, direction, branchId, stopPoint.map { it.toStopPoint() })

}

/**
 * extension function to map the whole line details response to the domain one
 */
fun LineDetailsResponseModel.toLineDetailsModel(): LineDetails {
    val sequence = this.stopPointSequences.map { it.toStopPointSequenceModel() }
    return LineDetails(this.lineId, this.lineName, sequence)
}





