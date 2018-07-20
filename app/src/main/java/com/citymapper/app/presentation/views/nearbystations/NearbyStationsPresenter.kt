package com.citymapper.app.presentation.views.nearbystations

import com.citymapper.app.app.AbsPresenter
import javax.inject.Inject

class NearbyStationsPresenter @Inject constructor() : AbsPresenter<NearbyStationsController>() {

    private lateinit var nearbyStationsVM: NearbyStationsVM

    fun initPresenter(nearbyStationsVM: NearbyStationsVM) {
        this.nearbyStationsVM = nearbyStationsVM
        nearbyStationsVM.loadStopPointsTest()
    }

}