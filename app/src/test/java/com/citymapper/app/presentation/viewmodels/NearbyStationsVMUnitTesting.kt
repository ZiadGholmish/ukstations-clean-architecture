package com.citymapper.app.presentation.viewmodels

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.usecase.FetchArrivalTimesUseCase
import com.citymapper.app.domain.usecase.FetchStopPointsUseCase
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsVM
import com.nhaarman.mockito_kotlin.times
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class NearbyStationsVMUnitTesting {

    private val stopPointsUseCase = Mockito.mock(FetchStopPointsUseCase::class.java)
    private val fetchArrivalTimesUseCase = Mockito.mock(FetchArrivalTimesUseCase::class.java)
    private val nearbyStationsVM by lazy { NearbyStationsVM(stopPointsUseCase, fetchArrivalTimesUseCase) }

    @Test
    fun getBufferoosExecutesUseCase() {
        `when`(nearbyStationsVM.loadStopPointsByLocation(0.0, 0.0))
        verify(stopPointsUseCase, times(1))
    }

}