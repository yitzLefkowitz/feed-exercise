package com.lightricks.feedexercise.data.fakes

import com.lightricks.feedexercise.data.database.FeedDao
import com.lightricks.feedexercise.data.database.FeedDatabase
import com.lightricks.feedexercise.data.database.FeedEntity
import kotlinx.coroutines.flow.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

fun createFeedDbMock(feedDao: FeedDao) = mock<FeedDatabase>().apply {
    whenever(feedDao()).thenReturn(feedDao)
}

class FeedDaoFake : FeedDao() {

    private val feedItems = MutableStateFlow<List<FeedEntity>>(emptyList())

    override fun getAllFeedItems(): Flow<List<FeedEntity>> {
        return feedItems.asStateFlow()
    }

    override suspend fun insertFeedItems(feedItems: Collection<FeedEntity>) {
        this.feedItems.update {
            it.plus(feedItems)
        }
    }

    override suspend fun clear() {
        this.feedItems.update { emptyList() }
    }
}