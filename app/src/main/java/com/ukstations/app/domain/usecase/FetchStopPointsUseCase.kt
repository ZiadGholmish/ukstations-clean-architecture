package com.ukstations.app.domain.usecase

import com.ukstations.app.domain.models.stoppoint.StopPointsResult
import com.ukstations.app.domain.repository.StopPointRepository
import com.ukstations.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class FetchStopPointsUseCase @Inject constructor(private val stopPointRepository: StopPointRepository, private val scheduler: BaseSchedulerProvider) {

    fun fetchStopPoints(stopTypes: String, radius: Int,
                        lat: Double, lon: Double): Observable<StopPointsResult> = stopPointRepository
            .fetchStopPointsByLocation(stopTypes, radius, lat, lon)
            .subscribeOn(scheduler.getIOScheduler())
            .observeOn(scheduler.getUiScheduler())
}