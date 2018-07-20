package com.citymapper.app.domain.usecase

import com.citymapper.app.data.remote.models.stops.StopPointsNetworkResult
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.repository.StopPointRepository
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class FetchStopPointsUseCase @Inject constructor(val stopPointRepository: StopPointRepository){


    fun fetchStopPoints(stopTypes: String, radius: Int,
                        lat: Double, lon: Double) : Observable<StopPointsNetworkResult> = stopPointRepository
            .fetchStopPointsByLocation(stopTypes , radius ,lat , lon )

}