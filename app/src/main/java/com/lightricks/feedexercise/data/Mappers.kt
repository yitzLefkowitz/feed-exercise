package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.data.database.FeedEntity
import com.lightricks.feedexercise.data.network.BASE_URL
import com.lightricks.feedexercise.data.network.FeedItemDto
import com.lightricks.feedexercise.data.network.THUMBNAIL_PATH

fun FeedItemDto.toFeedItemEntity() = FeedEntity(
    feedItemId = this.id,
    thumbnailUrl = BASE_URL + THUMBNAIL_PATH + this.thumbnailUrl,
    isPremium = this.isPremium
)

fun FeedEntity.toFeedItem() = FeedItem(
    id = id,
    feedItemId = feedItemId,
    thumbnailUrl = thumbnailUrl,
    isPremium = isPremium
)
