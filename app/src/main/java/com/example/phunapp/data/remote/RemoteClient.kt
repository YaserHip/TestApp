package com.example.phunapp.data.remote

import com.example.phunapp.utils.Constants.Companion.URL_BASE
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RemoteClient {

    val instance: RemoteService = Retrofit.Builder().run {
        val gson = GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create()
        baseUrl(URL_BASE)
        addConverterFactory(GsonConverterFactory.create(gson))
        client(createInterceptorClient())
        build()
    }.create(RemoteService::class.java)

    private fun createInterceptorClient(): OkHttpClient {
        val interceptor = Interceptor {
            val original = it.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            it.proceed(request)
        }
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

}