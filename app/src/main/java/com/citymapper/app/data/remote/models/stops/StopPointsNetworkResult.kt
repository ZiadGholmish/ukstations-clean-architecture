package com.citymapper.app.data.remote.models.stops

import retrofit2.Response

sealed class StopPointsNetworkResult {
    companion object {
        fun fromAggregateResponse(tubeModel: TubeModel): StopPointsNetworkResult {
            return StopPointsPayload.StopPointsModelsSealed(tubeModel.stopPoints)
        }

        fun fromErrorResponse(code: Int, message: String?): StopPointsNetworkResult {
            return when (code) {
                401 -> NetworkHttpError.UnAuthorizedRequest
                500 -> NetworkHttpError.InternalServerError
                else -> NetworkHttpError.UnknownError(code, message ?: "")
            }
        }
    }
}

/**
 * sealed to contains the response in case success
 */
sealed class StopPointsPayload : StopPointsNetworkResult() {
    data class StopPointsModelsSealed(val stopPoints: List<StopPoint>) : StopPointsPayload()
}

/**
 * format the error that come from the api so i can handle
 * and remove un necessary condition in on error function
 */
sealed class NetworkHttpError : StopPointsNetworkResult() {

    object UnAuthorizedRequest : NetworkHttpError()

    object InternalServerError : NetworkHttpError()

    data class UnknownError(val code: Int, val message: String) : NetworkHttpError()
}


/**
 * extension function to format the response and transfer to sealed classes
 */
fun Response<TubeModel>.toAggregateResult() =
        if (this.isSuccessful) {
            StopPointsNetworkResult.fromAggregateResponse(this.body()!!)
        } else {
            StopPointsNetworkResult.fromErrorResponse(this.code(), this.message())
        }