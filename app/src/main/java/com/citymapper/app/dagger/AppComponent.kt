package com.citymapper.app.dagger
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class , ViewModelModule::class])
interface AppComponent {

}