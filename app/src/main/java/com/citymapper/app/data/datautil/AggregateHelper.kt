package com.citymapper.app.data.datautil

import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import com.citymapper.app.domain.models.StopPintsPayLoad
import retrofit2.Response


/**
 * extension function to format the response and transfer to sealed classes
 */
fun Response<StopPointsResponseModel>.toAggregateResult() =
        if (this.isSuccessful) {
            StopPintsPayLoad.Data(this.body()!!.stopPoints)
        } else {
            Util.fromErrorResponse(this.code(), Util.formatErrorBody(this))
        }




