package com.citymapper.app.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.citymapper.app.data.remote.net.APIInterface
import com.citymapper.app.data.remote.repository.RepositoryImpl
import com.citymapper.app.domain.repository.StopPointRepository
import com.citymapper.app.util.scheduler.BaseSchedulerProvider
import com.citymapper.app.util.scheduler.SchedulerProviderImpl
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

    @Singleton
    @Provides
    fun provideStopRepo(apiInterface: APIInterface): StopPointRepository {
        return RepositoryImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProviderImpl
    }


}