package com.citymapper.app.app

import android.app.Application
import com.citymapper.app.dagger.AppComponent
import com.citymapper.app.dagger.AppModule
import com.citymapper.app.dagger.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary

class CitymapperApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this);
        initializeInjector()
    }

    private fun initializeInjector() {
        this.appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

}