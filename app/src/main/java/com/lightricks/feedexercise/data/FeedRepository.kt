package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.network.FeedApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is our data layer abstraction. Users of this class don't need to know
 * where the data actually comes from (network, database or somewhere else).
 */
interface FeedRepository {
    fun getFeed(): Flow<List<FeedItem>>
    suspend fun refreshFeed(): RefreshFeedResult
}

sealed class RefreshFeedResult {
    object Success : RefreshFeedResult()
    data class Error(val error: Throwable) : RefreshFeedResult()
}

@Singleton
class FeedRepositoryImpl @Inject constructor(
    private val feedApi: FeedApiService
): FeedRepository {

    override fun getFeed(): Flow<List<FeedItem>> = emptyFlow()

    override suspend fun refreshFeed(): RefreshFeedResult {
        val response = feedApi.getFeed()
        return try {
            // map the feed items
            // cache the feed items
            RefreshFeedResult.Success
        } catch (e: Exception) {
            RefreshFeedResult.Error(e)
        }
    }
}
