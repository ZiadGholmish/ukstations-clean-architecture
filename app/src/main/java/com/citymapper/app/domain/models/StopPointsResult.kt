package com.citymapper.app.domain.models

import com.citymapper.app.data.remote.models.stops.StopPoint

sealed class StopPointsResult

sealed class StopPintsPayLoad : StopPointsResult() {
    data class Data(val data: List<StopPoint>) : StopPintsPayLoad()
}

/**
 * format the error that come from the api so i can handle
 * and remove un necessary condition in on error function
 */
sealed class NetworkHttpError : StopPointsResult() {
    object UnAuthorizedRequest : NetworkHttpError()
    object InternalServerError : NetworkHttpError()
    data class GeneralError(val code: Int, val message: String) : NetworkHttpError()
}

