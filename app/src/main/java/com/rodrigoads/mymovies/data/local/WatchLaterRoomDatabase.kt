package com.rodrigoads.mymovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigoads.mymovies.data.local.daos.WatchLaterDao
import com.rodrigoads.mymovies.data.local.entities.WatchLaterEntity

@Database(entities = [WatchLaterEntity::class], version = 1)
abstract class WatchLaterRoomDatabase : RoomDatabase() {
    abstract fun watchLaterDao(): WatchLaterDao
}