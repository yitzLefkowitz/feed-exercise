package com.lightricks.feedexercise.data.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lightricks.feedexercise.data.FeedRepository
import com.lightricks.feedexercise.data.FeedRepositoryImpl
import com.lightricks.feedexercise.data.database.FeedDatabase
import com.lightricks.feedexercise.data.network.BASE_URL
import com.lightricks.feedexercise.data.network.FeedApiService
import com.lightricks.feedexercise.data.network.OkHttpClientFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    @Singleton
    @Provides
    fun provideFeedApiService(retrofit: Retrofit): FeedApiService =
        retrofit.create(FeedApiService::class.java)


    @Singleton
    @Provides
    fun provideFeedDb(@ApplicationContext context: Context): FeedDatabase {
        @Suppress("RedundantCompanionReference")
        return FeedDatabase.Factory.create(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Singleton
    @Binds
    abstract fun provideFeedRepository(feedRepository: FeedRepositoryImpl): FeedRepository
}
