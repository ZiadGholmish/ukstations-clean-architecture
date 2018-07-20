package com.citymapper.app.dagger

import android.app.Service
import android.content.Context
import com.citymapper.app.R
import com.citymapper.app.data.remote.net.APIInterface
import com.citymapper.app.data.remote.net.ServiceInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Named("BASE_URL_NAME")
    internal fun provideBaseUrl(context: Context): String {
        return context.getString(R.string.BASE_URL)
    }

    @Provides
    @Singleton
    internal fun provideJsonConverter(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideDebugInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClientBuilder(interceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)
        clientBuilder.addInterceptor(interceptor)
        clientBuilder.addInterceptor(ServiceInterceptor())
        return clientBuilder
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(client: OkHttpClient,
                                 @Named("BASE_URL_NAME") baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideApiInterface(retrofit: Retrofit): APIInterface {
        return retrofit.create(APIInterface::class.java)
    }

}