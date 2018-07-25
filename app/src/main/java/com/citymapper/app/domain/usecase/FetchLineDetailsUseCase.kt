package com.citymapper.app.domain.usecase

import com.citymapper.app.domain.models.linedetails.LineDetailsResult
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.util.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

class FetchLineDetailsUseCase @Inject constructor(private val stopPointRepository: StopPointRepository,
                                                  private val scheduler: BaseSchedulerProvider) {

    fun fetchStopPointArrivals(id: String, direction: String): Observable<LineDetailsResult> =
            stopPointRepository
                    .fetchLineDetails(id, direction)
                    .subscribeOn(scheduler.getIOScheduler())
                    .observeOn(scheduler.getUiScheduler())

}