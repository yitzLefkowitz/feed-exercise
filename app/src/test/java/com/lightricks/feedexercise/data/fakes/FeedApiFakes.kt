package com.lightricks.feedexercise.data.fakes

import com.lightricks.feedexercise.data.network.FeedApiService
import com.lightricks.feedexercise.data.network.GetFeedResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okio.buffer
import okio.source

object FeedFailException : Exception("Fake error")

object FeedApiFailFake : FeedApiService {
    override suspend fun getFeed(): GetFeedResponse {
        throw FeedFailException
    }
}

object FeedApiSuccessFake : FeedApiService {
    override suspend fun getFeed(): GetFeedResponse {
        return FeedResponseLoader.feedResponse
    }
}

object FeedResponseLoader {

    val feedResponse: GetFeedResponse by lazy { loadFeedResponse() }

    private fun loadFeedResponse(): GetFeedResponse {
        val decoder = Json {
            this.ignoreUnknownKeys = true
            this.isLenient = true
        }
        return decoder.decodeFromString(readFeedResponse())
    }

    private fun readFeedResponse(): String {
        val inputStream = javaClass.classLoader?.getResourceAsStream("get_feed_response.json")!!
        val buffer = inputStream.source().buffer()
        return buffer.use { it.readUtf8() }
    }
}
