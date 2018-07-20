package com.citymapper.app.app

import android.app.Application
import com.citymapper.app.dagger.AppComponent
import com.citymapper.app.dagger.AppModule
import com.citymapper.app.dagger.DaggerAppComponent

class CitymapperApp : Application() {

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