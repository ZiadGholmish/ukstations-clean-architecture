package com.ukstations.app.domain.usecase

import com.ukstations.app.domain.models.arrivals.StopArrivalsResult
import com.ukstations.app.domain.repository.StopPointRepository
import com.ukstations.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class FetchArrivalTimesUseCase @Inject constructor(private val stopPointRepository: StopPointRepository,
                                                   private val scheduler: BaseSchedulerProvider) {

    fun fetchStopPointArrivals(id: String): Observable<StopArrivalsResult> = stopPointRepository
            .fetchStopPointArrivals(id)
            .subscribeOn(scheduler.getIOScheduler())
            .observeOn(scheduler.getUiScheduler())

}