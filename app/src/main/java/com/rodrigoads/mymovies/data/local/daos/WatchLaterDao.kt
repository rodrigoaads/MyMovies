package com.rodrigoads.mymovies.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodrigoads.mymovies.data.local.entities.WatchLaterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchLaterDao {

    @Query("SELECT * from watch_later")
    fun getAllWatchLaterMovies() : Flow<List<WatchLaterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWatchLaterMovie(watchLaterEntity: WatchLaterEntity)

    @Query("DELETE from watch_later WHERE id = :id")
    suspend fun removeWatchLaterMovie(id : Int)
}