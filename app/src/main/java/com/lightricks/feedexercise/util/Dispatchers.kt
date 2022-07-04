package com.lightricks.feedexercise.util

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("PropertyName")
interface FeedDispatchers {
    val Default: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val IO: CoroutineDispatcher
    val Global: CoroutineScope
}

@Singleton
class FeedDispatchersImpl @Inject constructor(appScope: AppScope) : FeedDispatchers {
    override val IO: CoroutineDispatcher = Dispatchers.IO
    override val Main: CoroutineDispatcher = Dispatchers.Main
    override val Default: CoroutineDispatcher = Dispatchers.Default
    override val Global: CoroutineScope = appScope
}

@Singleton
class AppScope @Inject constructor() : CoroutineScope by MainScope()

@InstallIn(SingletonComponent::class)
@Module
abstract class FeedDispatchersBindModule {

    @Binds
    abstract fun bindFeedDispatchers(feedDispatchersImpl: FeedDispatchersImpl): FeedDispatchers
}