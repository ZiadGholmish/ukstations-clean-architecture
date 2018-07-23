package com.citymapper.app.data.remote.net

import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeEntity
import com.citymapper.app.data.remote.models.stops.StopPointsResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface APIInterface {

    @GET(APIConstants.API_STOP_POINTS_PREFIX)
    fun fetchStopPointsByLocation(@Query("stopTypes") stopTypes: String,
                                  @Query("radius") radius: Int,
                                  @Query("lat") lat: Double,
                                  @Query("lon") lon: Double,
                                  @Query("app_id") app_id: String,
                                  @Query("app_key") app_key: String
    ): Observable<Response<StopPointsResponseModel>>

    @GET(APIConstants.API_STOP_POINTS_ARRIVALS_PREFIX)
    fun fetchStopPointArrivals(@Path("id") id: String): Observable<Response<List<ArrivalTimeEntity>>>

}