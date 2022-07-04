package com.lightricks.feedexercise.data

import app.cash.turbine.test
import com.lightricks.feedexercise.data.database.FeedDao
import com.lightricks.feedexercise.data.database.FeedDatabase
import com.lightricks.feedexercise.data.fakes.*
import com.lightricks.feedexercise.util.FeedDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedRepoTest {

    private lateinit var feedDao: FeedDao
    private lateinit var feedDb: FeedDatabase
    private val dispatcher = StandardTestDispatcher()
    private val dispatchers = object : FeedDispatchers {
        override val Default: CoroutineDispatcher = dispatcher
        override val Main: CoroutineDispatcher = dispatcher
        override val IO: CoroutineDispatcher = dispatcher
        override val Global: CoroutineScope = CoroutineScope(dispatcher)
    }

    @Before
    fun setUp() {
        feedDao = FeedDaoFake()
        feedDb = createFeedDbMock(feedDao)
        runBlocking { feedDao.clear() }
    }

    @After
    fun tearDown() {
        runBlocking { feedDao.clear() }
    }

    @Test
    fun `test fetch success`() = runTest(dispatcher) {
        val feedRepo: FeedRepository = FeedRepositoryImpl(
            FeedApiSuccessFake,
            feedDb,
            dispatchers
        )
        feedRepo.getFeed().test {
            Assert.assertTrue(awaitItem().isEmpty())

            val refreshFeedResult = feedRepo.refreshFeed()
            Assert.assertEquals(RefreshFeedResult.Success, refreshFeedResult)
            Assert.assertEquals(FeedResponseLoader.feedResponse.feed.size, awaitItem().size)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `test fetch failure`() = runTest(dispatcher) {
        val feedRepo: FeedRepository = FeedRepositoryImpl(
            FeedApiFailFake,
            feedDb,
            dispatchers
        )
        feedRepo.getFeed().test {

            val refreshFeedResult = feedRepo.refreshFeed()
            Assert.assertEquals(RefreshFeedResult.Error(FeedFailException), refreshFeedResult)

            cancelAndConsumeRemainingEvents()
        }
    }
}
