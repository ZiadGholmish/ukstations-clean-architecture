package com.ukstations.app.data.datautil

import com.ukstations.app.domain.models.arrivals.StopArrivalNetworkHttpError
import com.ukstations.app.domain.models.arrivals.StopArrivalsResult
import com.ukstations.app.domain.models.linedetails.LineDetailsNetworkHttpError
import com.ukstations.app.domain.models.linedetails.LineDetailsResult
import com.ukstations.app.domain.models.stoppoint.NetworkHttpError
import com.ukstations.app.domain.models.stoppoint.StopPointsResult
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject


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

    fun fromLineDetailsErrorResponse(code: Int, message: String?): LineDetailsResult {
        return when (code) {
            401 -> LineDetailsNetworkHttpError.Error(code, message
                    ?: "")
            500 -> LineDetailsNetworkHttpError.Error(code, message
                    ?: "")
            else -> LineDetailsNetworkHttpError.Error(code, message
                    ?: "")
        }
    }

}
