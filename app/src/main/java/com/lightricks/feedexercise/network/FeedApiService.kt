package com.lightricks.feedexercise.network

import kotlinx.serialization.Serializable
import retrofit2.http.GET

interface FeedApiService {
    @GET("/Android/demo/feed.json")
    suspend fun getFeed(): GetFeedResponse
}

@Serializable
data class GetFeedResponse(
    val feed: List<FeedItemDto>
)
