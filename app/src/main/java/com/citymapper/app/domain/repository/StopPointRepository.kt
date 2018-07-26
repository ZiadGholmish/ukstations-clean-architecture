package com.citymapper.app.domain.repository

import com.citymapper.app.domain.models.arrivals.ArrivalTimeModel
import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.models.linedetails.LineDetailsResult
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import io.reactivex.Observable

interface StopPointRepository {

    fun fetchStopPointsByLocation(stopTypes: String, radius: Int, lat: Double, lon: Double): Observable<StopPointsResult>

    fun fetchStopPointArrivals(id: String): Observable<StopArrivalsResult>

    fun fetchLineDetails(id: String, direction: String): Observable<LineDetailsResult>
}