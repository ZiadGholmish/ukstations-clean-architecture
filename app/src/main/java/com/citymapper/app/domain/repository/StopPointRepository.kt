package com.citymapper.app.domain.repository

import com.citymapper.app.data.remote.models.arrivaltimes.ArrivalTimeModel
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import io.reactivex.Observable

interface StopPointRepository {

    fun fetchStopPointsByLocation(stopTypes: String, radius: Int, lat: Double, lon: Double): Observable<StopPointsResult>

    fun fetchStopPointArrivals(id: String): Observable<List<ArrivalTimeModel>>
}