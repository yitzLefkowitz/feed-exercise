package com.lightricks.feedexercise.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class FeedDao {

    @Query("SELECT * FROM ${FeedEntity.TABLE_NAME}")
    abstract fun getAllFeedItems()

    @Transaction
    suspend fun replaceFeedItems(feedItems: List<FeedEntity>) {
        clear()
        insertFeedItems(feedItems)
    }

    @Insert
    abstract suspend fun insertFeedItems(feedItems: Collection<FeedEntity>)

    @Query("DELETE FROM ${FeedEntity.TABLE_NAME}")
    abstract suspend fun clear()
}
