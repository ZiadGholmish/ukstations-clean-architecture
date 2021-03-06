package com.ukstations.app.dagger

import com.ukstations.app.presentation.views.linedetails.LineDetailsActivity
import com.ukstations.app.presentation.views.nearbystations.NearbyStationsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(nearbyStationsActivity: NearbyStationsActivity)

    fun inject(lineDetailsActivity: LineDetailsActivity)
}