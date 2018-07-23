package com.citymapper.app.data.remote.net

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ServiceInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
                .addHeader("Cache-Control", "no-cache")
        return chain.proceed(requestBuilder.build())
    }
}