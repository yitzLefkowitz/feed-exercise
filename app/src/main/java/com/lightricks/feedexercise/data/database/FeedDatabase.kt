package com.lightricks.feedexercise.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FeedEntity::class], version = 1, exportSchema = true)
abstract class FeedDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao

    companion object Factory {
        fun create(context: Context): FeedDatabase = Room.databaseBuilder(
            context.applicationContext,
            FeedDatabase::class.java,
            "feed_database"
        ).build()
    }
}
