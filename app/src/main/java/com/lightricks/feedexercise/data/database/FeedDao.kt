package com.lightricks.feedexercise.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
abstract class FeedDao {

    @Query("SELECT * FROM ${FeedEntity.TABLE_NAME}")
    abstract fun getAllFeedItems(): Flow<List<FeedEntity>>

    @Transaction
    suspend fun replaceFeedItems(feedItems: Collection<FeedEntity>) {
        clear()
        insertFeedItems(feedItems)
    }

    @Insert
    abstract suspend fun insertFeedItems(feedItems: Collection<FeedEntity>)

    @Query("DELETE FROM ${FeedEntity.TABLE_NAME}")
    abstract suspend fun clear()
}
