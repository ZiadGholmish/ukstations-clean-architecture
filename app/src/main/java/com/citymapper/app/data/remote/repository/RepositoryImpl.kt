package com.citymapper.app.data.remote.repository

import com.citymapper.app.data.datautil.toAggregateArrivalsResult
import com.citymapper.app.data.datautil.toAggregatePointsResult
import com.citymapper.app.data.remote.net.APIConstants
import com.citymapper.app.data.remote.net.APIInterface
import com.citymapper.app.domain.models.arrivals.ArrivalTimeModel
import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.repository.StopPointRepository
import io.reactivex.Observable


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val apiInterface: APIInterface) : StopPointRepository {

    /**
     *fetch the stop points regarding the user location and transform the response
     * @param stopTypes  the types of the points we want to get
     * @param radius  the distance to get the seround points
     * @param lat  latitude of the user locaiton
     * @param lon longitude of the user location
     */
    override fun fetchStopPointsByLocation(stopTypes: String, radius: Int,
                                           lat: Double, lon: Double): Observable<StopPointsResult> {
        return apiInterface.
                fetchStopPointsByLocation(stopTypes, radius, lat, lon, APIConstants.app_id, APIConstants.app_key)
                .map { it.toAggregatePointsResult() }
    }

    override fun fetchStopPointArrivals(id: String): Observable<StopArrivalsResult> {
        return apiInterface.fetchStopPointArrivals(id,APIConstants.app_id, APIConstants.app_key)
                .map { it.toAggregateArrivalsResult() }
    }

    override fun fetchLineStopPoints(id: String, direction: String) {
    }
}