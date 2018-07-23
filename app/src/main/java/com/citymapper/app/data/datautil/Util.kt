package com.citymapper.app.data.datautil

import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import com.citymapper.app.domain.models.NetworkHttpError
import com.citymapper.app.domain.models.StopPointsResult
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


object Util {
    /**
     * format the error body to json and try to get the message if found from the api
     */
    fun formatErrorBody(response: Response<StopPointsResponseModel>): String {
        return try {
            val errorJson = JSONObject(response.errorBody()?.string())
            errorJson.getString("message")
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
            response.message()
        }
    }


    fun fromErrorResponse(code: Int, message: String?): StopPointsResult {
        return when (code) {
            401 -> NetworkHttpError.UnAuthorizedRequest
            500 -> NetworkHttpError.InternalServerError
            else -> NetworkHttpError.GeneralError(code, message
                    ?: "")
        }
    }
}
