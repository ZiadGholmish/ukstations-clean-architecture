package com.citymapper.app.data.datautil

import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeEntity
import com.citymapper.app.data.remote.models.stops.StopPointEntity
import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import com.citymapper.app.domain.models.arrivals.ArrivalTimeModel
import com.citymapper.app.domain.models.arrivals.StopArrivalsPayLoad
import com.citymapper.app.domain.models.stoppoint.StopPintsPayLoad
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import org.w3c.dom.Entity
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

fun Response<List<ArrivalTimeEntity>>.toAggregateArrivalsResult() =
        if (this.isSuccessful) {
            StopArrivalsPayLoad.Data(this.body()!!.map { it.toArrivalTime() })
        } else {
            Util.fromArrivalErrorResponse(this.code(), Util.formatErrorBody(this.errorBody()))
        }


/**
 * extension function to map the entity to the stop point domain model
 */
fun StopPointEntity.toStopPoint(): StopPoint {
    return StopPoint(this.id, this.commonName, this.distance, this.lat, this.lon, listOf())
}

fun ArrivalTimeEntity.toArrivalTime(): ArrivalTimeModel {
    return ArrivalTimeModel(this.id, this.naptanId, this.lineName,
            this.destinationName, this.timeToStation,
            this.expectedArrival, this.towards, this.direction,
            this.platformName)
}





