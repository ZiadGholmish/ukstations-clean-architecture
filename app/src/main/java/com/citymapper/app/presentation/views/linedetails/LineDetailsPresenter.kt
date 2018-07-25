package com.citymapper.app.presentation.views.linedetails

import com.citymapper.app.app.AbsPresenter
import javax.inject.Inject

class LineDetailsPresenter @Inject constructor() : AbsPresenter<LineDetailsController>() {

    private lateinit var liveDetailsVM: LineDetailsVM

    fun initPresenter(liveDetailsVM: LineDetailsVM) {
        this.liveDetailsVM = liveDetailsVM
    }

}