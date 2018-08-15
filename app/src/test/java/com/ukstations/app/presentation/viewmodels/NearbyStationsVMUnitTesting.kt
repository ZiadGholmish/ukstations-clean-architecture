package com.ukstations.app.presentation.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.ukstations.app.domain.models.stoppoint.StopPoint
import com.ukstations.app.domain.models.stoppoint.StopPointsResult
import com.ukstations.app.domain.usecase.FetchArrivalTimesUseCase
import com.ukstations.app.domain.usecase.FetchStopPointsUseCase
import com.ukstations.app.presentation.views.nearbystations.NearbyStationsVM
import com.ukstations.app.utils.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NearbyStationsVMUnitTesting {

    inline fun <reified T> lambdaMock(): T = mock(T::class.java)


    @get:Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @get:Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Mock
    private lateinit var observer: Observer<List<StopPoint>>


    @Mock
    private lateinit var fetchArrivalTimesUseCase: FetchArrivalTimesUseCase

    @Mock
    private lateinit var stopPointResult: StopPointsResult

    @Mock
    private lateinit var stopPointsUseCase: FetchStopPointsUseCase

    private val nearbyStationsVM by lazy { NearbyStationsVM(stopPointsUseCase, fetchArrivalTimesUseCase) }

    @Before
    fun initTest() {
        reset(stopPointsUseCase, observer)
    }

//    @Test
//    fun testCallingMethod() {
//
//        val fakeObservable = Observable.just(stopPointResult)
//        `when`(stopPointsUseCase.fetchStopPoints(ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble()))
//                .thenReturn(fakeObservable)
//        nearbyStationsVM.stopPointsLiveData.observeForever(observer)
//        nearbyStationsVM.fetchStopPointsByLocation(0.0, 0.0)
//
//
//    }

}