package com.lightricks.feedexercise.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lightricks.feedexercise.data.FeedRepository
import com.lightricks.feedexercise.data.FeedRepositoryImpl
import com.lightricks.feedexercise.data.OkHttpClientFactory
import com.lightricks.feedexercise.network.FeedApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClientFactory.create()

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://assets.swishvideoapp.com/")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideFeedApiService(retrofit: Retrofit): FeedApiService =
        retrofit.create(FeedApiService::class.java)
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Singleton
    @Binds
    abstract fun provideFeedRepository(feedRepository: FeedRepositoryImpl): FeedRepository
}
