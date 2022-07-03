package com.lightricks.feedexercise.data.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.lightricks.feedexercise.data.database.FeedEntity.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index("feedItemId")]
)
data class FeedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val feedItemId: String,
    val thumbnailUrl: String,
    val isPremium: Boolean
) {
    companion object {
        const val TABLE_NAME = "feed_items"
    }
}
