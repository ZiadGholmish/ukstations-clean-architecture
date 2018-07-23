package com.citymapper.app.domain.usecase

import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class FetchStopPointsUseCase @Inject constructor(private val stopPointRepository: StopPointRepository, private val scheduler: BaseSchedulerProvider) {

    fun fetchStopPoints(stopTypes: String, radius: Int,
                        lat: Double, lon: Double): Observable<StopPointsResult> = stopPointRepository
            .fetchStopPointsByLocation(stopTypes, radius, lat, lon)
            .subscribeOn(scheduler.getIOScheduler())
            .observeOn(scheduler.getUiScheduler())
}