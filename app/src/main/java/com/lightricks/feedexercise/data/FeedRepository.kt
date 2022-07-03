package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.data.database.FeedDatabase
import com.lightricks.feedexercise.data.network.FeedApiService
import com.lightricks.feedexercise.util.FeedDispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
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
    private val feedApi: FeedApiService,
    feedDatabase: FeedDatabase,
    private val dispatchers: FeedDispatchers
) : FeedRepository {

    private val feedDao = feedDatabase.feedDao()

    override fun getFeed(): Flow<List<FeedItem>> {
        return feedDao.getAllFeedItems()
            .flowOn(dispatchers.IO)
            .map { feedItems -> feedItems.map { it.toFeedItem() } }
            .flowOn(dispatchers.Default)
    }

    override suspend fun refreshFeed(): RefreshFeedResult {
        return withContext(dispatchers.IO) {
            try {
                val response = feedApi.getFeed()
                val feedEntities = withContext(dispatchers.Default) {
                    response.feed.map { it.toFeedItemEntity() }
                }
                feedDao.replaceFeedItems(feedEntities)
                RefreshFeedResult.Success
            } catch (e: Exception) {
                RefreshFeedResult.Error(e)
            }
        }
    }
}
