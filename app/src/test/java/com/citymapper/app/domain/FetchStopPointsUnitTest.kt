package com.citymapper.app.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.citymapper.app.utils.FakeSchedulerImpl
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FetchStopPointsUnitTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val stopPointsRepo = mock(RepositoryImpl::class.java)
    private val schedulerProvider: FakeSchedulerImpl = FakeSchedulerImpl()
    private val stopPointResult = mock(StopPointsResult::class.java)
    private val fetchStopPoints by lazy { FetchStopPointsUseCase(stopPointsRepo, schedulerProvider) }

    //fake values
    private val stopTypes = "TestStopPointTypes"
    private val radius = 1000
    private val lat = 0.0
    private val long = 0.0

    /**
     *test the success case when the use case calling the
     * repo to get the available stop points
     */
    @Test
    fun testGetStopPointsCompleted() {

        val fakeObservable = Observable.just(stopPointResult)

        `when`(stopPointsRepo.fetchStopPointsByLocation(stopTypes, radius, lat, long))
                .thenReturn(fakeObservable)

        fetchStopPoints.fetchStopPoints(stopTypes, radius, lat, long)
                .test()
                .assertComplete()
    }

    /**
     * test that everything is fine when there is error happens
     */
    @Test
    fun testGetStopPointsFailed() {

        val response = Throwable("Error response")

        `when`(stopPointsRepo.fetchStopPointsByLocation(stopTypes, radius, lat, long))
                .thenReturn(Observable.error(response))

        fetchStopPoints.fetchStopPoints(stopTypes, radius, lat, long)
                .test()
                .assertError(response)
    }

    /**
     * test that everything is fine when there is a response and the
     * sealed response class is the right type
     */
    @Test
    fun testGetArrivalTimesForStopPointResponse() {

        val fakeObservable = Observable.just(stopPointResult)

        `when`(stopPointsRepo.fetchStopPointsByLocation(stopTypes, radius, lat, long))
                .thenReturn(fakeObservable)

        fetchStopPoints.fetchStopPoints(stopTypes, radius, lat, long)
                .test()
                .assertValue(stopPointResult)
    }


}