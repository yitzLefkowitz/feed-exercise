package com.lightricks.feedexercise.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface FeedApiService {
    @GET(FEED_ITEMS_PATH)
    suspend fun getFeed(): GetFeedResponse
}

@Serializable
data class GetFeedResponse(
    @SerialName("templatesMetadata")
    val feed: List<FeedItemDto>
)
