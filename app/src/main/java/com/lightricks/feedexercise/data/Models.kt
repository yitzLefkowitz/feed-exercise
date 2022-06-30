package com.lightricks.feedexercise.data

import kotlinx.serialization.Serializable

/**
 * This data class is our internal representation of a feed item.
 * In a real-life application this is template meta-data for a user's project.
 * The rest of the properties are left out for brevity.
 */
@Serializable
data class FeedItem(
    val id: Int = 0,
    val feedItemId: String,
    val thumbnailUrl: String,
    val isPremium: Boolean
)
