package com.citymapper.app.domain.models.stoppoint

sealed class StopPointsResult

sealed class StopPintsPayLoad : StopPointsResult() {
    data class Data(val data: List<StopPoint>) : StopPintsPayLoad()
}

/**
 * format the error that come from the api so i can handle
 * and remove un necessary condition in on error function
 */
sealed class NetworkHttpError : StopPointsResult() {
    data class Error(val code: Int, val message: String) : NetworkHttpError()
}

