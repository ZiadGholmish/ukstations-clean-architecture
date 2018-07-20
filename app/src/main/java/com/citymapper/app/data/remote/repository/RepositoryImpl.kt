package com.citymapper.app.data.remote.repository

import com.citymapper.app.data.remote.models.stops.StopPointsNetworkResult
import com.citymapper.app.data.remote.models.stops.TubeModel
import com.citymapper.app.data.remote.models.stops.toAggregateResult
import com.citymapper.app.data.remote.net.APIConstants
import com.citymapper.app.data.remote.net.APIInterface
import com.citymapper.app.domain.repository.StopPointRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiInterface: APIInterface) : StopPointRepository {

    /**
     *fetch the stop points regarding the user location and transform the response
     * @param stopTypes  the types of the points we want to get
     * @param radius  the distance to get the seround points
     * @param lat  latitude of the user locaiton
     * @param lon longitude of the user location
     */
    override fun fetchStopPointsByLocation(stopTypes: String, radius: Int,
                                           lat: Double, lon: Double): Observable<StopPointsNetworkResult> {
        return apiInterface.fetchStopPointsByLocation(stopTypes, radius, lat, lon, APIConstants.app_id, APIConstants.app_key)
                .map { it.toAggregateResult() }
    }
}