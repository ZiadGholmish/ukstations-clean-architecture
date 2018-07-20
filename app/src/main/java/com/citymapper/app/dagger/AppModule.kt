package carriage.com.carriagerestaurantapp.dagger

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(application: Application) {

    private var application: Application = application

    @Singleton
    @Provides
    fun provideApplication(): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideShared(): SharedPreferences {
        return application.getSharedPreferences("citymapper", 0)
    }

}