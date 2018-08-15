package com.ukstations.app.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ukstations.app.data.remote.net.APIInterface
import com.ukstations.app.data.remote.repository.RepositoryImpl
import com.ukstations.app.domain.repository.StopPointRepository
import com.ukstations.app.util.scheduler.BaseSchedulerProvider
import com.ukstations.app.util.scheduler.SchedulerProviderImpl
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
    fun provideStopRepo(apiInterface: APIInterface): StopPointRepository {
        return RepositoryImpl(apiInterface)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProviderImpl
    }


}