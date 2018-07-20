package com.citymapper.app.dagger

import carriage.com.carriagerestaurantapp.dagger.AppModule
import carriage.com.carriagerestaurantapp.dagger.NetworkModule
import carriage.com.carriagerestaurantapp.dagger.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class , ViewModelModule::class])
interface AppComponent {


}