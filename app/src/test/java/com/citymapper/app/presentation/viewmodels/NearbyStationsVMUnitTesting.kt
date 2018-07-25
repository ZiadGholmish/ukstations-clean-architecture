package com.citymapper.app.presentation.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.Observer
import com.citymapper.app.data.remote.models.RequestState
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.models.stoppoint.StopPoint
import com.citymapper.app.domain.models.stoppoint.StopPointsResult
import com.citymapper.app.domain.usecase.FetchArrivalTimesUseCase
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsVM
import com.citymapper.app.utils.RxImmediateSchedulerRule
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.disposables.Disposable
import io.reactivex.Scheduler
import org.junit.BeforeClass
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit


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
//        nearbyStationsVM.loadStopPointsByLocation(0.0, 0.0)
//
//
//    }

}