package com.citymapper.app.domain.usecase

import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class FetchArrivalTimesUseCase @Inject constructor(private val stopPointRepository: StopPointRepository,
                                                   private val scheduler: BaseSchedulerProvider) {

    fun fetchStopPointArrivals(id: String): Observable<StopArrivalsResult> = stopPointRepository
            .fetchStopPointArrivals(id)
            .subscribeOn(scheduler.getIOScheduler())
            .observeOn(scheduler.getUiScheduler())

}