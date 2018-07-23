package com.citymapper.app.data.datautil

import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import com.citymapper.app.domain.models.arrivals.StopArrivalNetworkHttpError
import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.models.stoppoint.NetworkHttpError
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response


object Util {
    /**
     * format the error body to json and try to get the message if found from the api
     */
    fun formatErrorBody(errorBody: ResponseBody?): String {
        return try {
            val errorJson = JSONObject(errorBody?.string())
            errorJson.getString("message")
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
            ""
        }
    }

    fun fromErrorResponse(code: Int, message: String?): StopPointsResult {
        return when (code) {
            401 -> NetworkHttpError.Error(code, message
                    ?: "")
            500 -> NetworkHttpError.Error(code, message
                    ?: "")
            else -> NetworkHttpError.Error(code, message
                    ?: "")
        }
    }


    fun fromArrivalErrorResponse(code: Int, message: String?): StopArrivalsResult {
        return when (code) {
            401 -> StopArrivalNetworkHttpError.Error(code, message
                    ?: "")
            500 -> StopArrivalNetworkHttpError.Error(code, message
                    ?: "")
            else -> StopArrivalNetworkHttpError.Error(code, message
                    ?: "")
        }
    }

}
