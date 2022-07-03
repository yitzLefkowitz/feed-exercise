package com.lightricks.feedexercise.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedItemDto(
    val id: String,
    @SerialName("templateThumbnailURI")
    val thumbnailUrl: String,
    val isPremium: Boolean
)
