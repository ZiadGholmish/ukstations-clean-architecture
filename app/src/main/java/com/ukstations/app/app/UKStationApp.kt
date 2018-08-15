package com.ukstations.app.app

import android.app.Application
import com.ukstations.app.dagger.AppComponent
import com.ukstations.app.dagger.AppModule
import com.ukstations.app.dagger.DaggerAppComponent


class UKStationApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}