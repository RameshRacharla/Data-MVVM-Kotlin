package com.rameshracharla.datamvvmkotlin.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val BASE_URL = "https://reqres.in/api/"
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit? {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(45, TimeUnit.SECONDS)
        builder.writeTimeout(45, TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)
        val client = builder.build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}