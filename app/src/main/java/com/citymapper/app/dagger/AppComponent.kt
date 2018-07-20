package com.citymapper.app.dagger
import com.citymapper.app.presentation.views.nearbystations.NearbyStationsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class , ViewModelModule::class])
interface AppComponent {

    fun inject(nearbyStationsActivity: NearbyStationsActivity)
}