package com.citymapper.app.domain.models.linedetails

sealed class LineDetailsResult


sealed class LineDetailsPayLoad : LineDetailsResult() {
    data class Data(val data: LineDetailsModel) : LineDetailsPayLoad()
}

/**
 * format the error that come from the api so i can handle
 * and remove un necessary condition in on error function
 */
sealed class LineDetailsNetworkHttpError : LineDetailsResult() {
    data class Error(val code: Int, val message: String) : LineDetailsNetworkHttpError()
}


