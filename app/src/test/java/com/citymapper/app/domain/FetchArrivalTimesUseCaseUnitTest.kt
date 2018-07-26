package com.citymapper.app.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.models.arrivals.StopArrivalsResult
import com.citymapper.app.domain.usecase.FetchArrivalTimesUseCase
import com.citymapper.app.utils.FakeSchedulerImpl
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchArrivalTimesUseCaseUnitTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val stopPointsRepo = Mockito.mock(RepositoryImpl::class.java)
    private val schedulerProvider: FakeSchedulerImpl = FakeSchedulerImpl()
    private val stopPointArrivalResult = Mockito.mock(StopArrivalsResult::class.java)

    private val fetchArrivalTimesUseCase by lazy { FetchArrivalTimesUseCase(stopPointsRepo, schedulerProvider) }

    //fake values
    private val stopPointId = "-1"

    /**
     *test the success case when the use case calling the
     * repo to get the available arrival times to stop point
     */
    @Test
    fun testGetArrivalTimesForStopPointCompleted() {

        val fakeObservable = Observable.just(stopPointArrivalResult)

       `when`(stopPointsRepo.fetchStopPointArrivals(stopPointId))
                .thenReturn(fakeObservable)

        fetchArrivalTimesUseCase.fetchStopPointArrivals(stopPointId)
                .test()
                .assertComplete()
    }


    /**
     * test that everything is fine when there is error happens
     */
    @Test
    fun testGetArrivalTimesFroStopPointFailed() {

        val response = Throwable("Error response")

        `when`(stopPointsRepo.fetchStopPointArrivals(stopPointId))
                .thenReturn(Observable.error(response))

        fetchArrivalTimesUseCase.fetchStopPointArrivals(stopPointId)
                .test()
                .assertError(response)
    }


    /**
     * test that everything is fine when there is a response and the
     * sealed response class is the right type
     */
    @Test
    fun testGetStopPointsResponse() {

        val fakeObservable = Observable.just(stopPointArrivalResult)

        `when`(stopPointsRepo.fetchStopPointArrivals(stopPointId))
                .thenReturn(fakeObservable)

        fetchArrivalTimesUseCase.fetchStopPointArrivals(stopPointId)
                .test()
                .assertValue(stopPointArrivalResult)
    }

}