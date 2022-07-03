package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.data.database.FeedEntity
import com.lightricks.feedexercise.data.network.FeedItemDto

fun FeedItemDto.toFeedItemEntity() = FeedEntity(
    feedItemId = this.id,
    thumbnailUrl = this.thumbnailUrl,
    isPremium = this.isPremium
)

fun FeedEntity.toFeedItem() = FeedItem(
    id = id,
    feedItemId = feedItemId,
    thumbnailUrl = thumbnailUrl,
    isPremium = isPremium
)
