package com.sai.fabula.di

import com.sai.fabula.api.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiModule {

    fun getApiService(): ApiService = Retrofit.Builder()
        .baseUrl(ApiService.URL)
        .addConverterFactory(MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        ))
        .build()
        .create(ApiService::class.java)
}
