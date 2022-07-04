package com.lightricks.feedexercise.data

import com.lightricks.feedexercise.data.database.FeedEntity
import com.lightricks.feedexercise.data.network.BASE_URL
import com.lightricks.feedexercise.data.network.FeedItemDto
import com.lightricks.feedexercise.data.network.THUMBNAIL_PATH
import org.junit.Test
import java.util.*

class MappersTests {

    @Test
    fun `map dto to entity`() {
        val feedItemDto = FeedItemDto(
            id = "id",
            thumbnailUrl = "BikeThumbnail.jpg",
            isPremium = false
        )
        val feedItemEntity = feedItemDto.toFeedItemEntity()

        assert(feedItemEntity.id == 0)
        assert(feedItemEntity.feedItemId == feedItemDto.id)
        assert(feedItemEntity.thumbnailUrl == BASE_URL + THUMBNAIL_PATH + feedItemDto.thumbnailUrl)
        assert(feedItemEntity.isPremium == feedItemDto.isPremium)
    }

    @Test
    fun `map entity to feedItem`() {
        val feedItemEntity = FeedEntity(
            feedItemId = UUID.randomUUID().toString(),
            thumbnailUrl = "https://assets.swishvideoapp.com/Android/demo/catalog/thumbnails/BikeThumbnail.jpg",
            isPremium = true
        )
        val feedItem = feedItemEntity.toFeedItem()
        assert(feedItem.id == feedItemEntity.id)
        assert(feedItem.feedItemId == feedItemEntity.feedItemId)
        assert(feedItem.thumbnailUrl == feedItemEntity.thumbnailUrl)
        assert(feedItem.isPremium == feedItemEntity.isPremium)
    }
}