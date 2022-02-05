package com.sample.jsonplaceholder.di

import com.sample.jsonplaceholder.network.JsonPlaceholderApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    const val baseUrl = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun provideBaseUrl() = baseUrl

    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String): JsonPlaceholderApi =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderApi::class.java)
}