package com.rodrigoads.mymovies.framework.di

import android.content.Context
import androidx.room.Room
import com.rodrigoads.mymovies.data.local.WatchLaterRoomDatabase
import com.rodrigoads.mymovies.data.local.daos.WatchLaterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideWatchLaterRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        WatchLaterRoomDatabase::class.java,
        "watch_later_database"
    ).build()

    @Provides
    fun provideWatchLaterDao(db: WatchLaterRoomDatabase): WatchLaterDao {
        return db.watchLaterDao()
    }
}