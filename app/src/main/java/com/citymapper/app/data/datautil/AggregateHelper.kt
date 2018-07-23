package com.citymapper.app.data.datautil

import com.citymapper.app.data.remote.models.stops.StopPointEntity
import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import com.citymapper.app.domain.models.stoppoint.StopPintsPayLoad
import com.citymapper.app.domain.models.stoppoint.StopPoint
import retrofit2.Response


/**
 * extension function to format the response and transfer to sealed classes
 */
fun Response<StopPointsResponseModel>.toAggregateResult() =
        if (this.isSuccessful) {
            StopPintsPayLoad.Data(this.body()!!.stopPoints.map { it.toStopPoint() })
        } else {
            Util.fromErrorResponse(this.code(), Util.formatErrorBody(this))
        }


/**
 * extenstopm function to map the entity to the stop point domain model
 */
fun StopPointEntity.toStopPoint(): StopPoint {
    return StopPoint(this.commonName, this.distance, this.lat, this.lon)
}





