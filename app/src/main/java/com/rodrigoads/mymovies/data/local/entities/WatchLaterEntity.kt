package com.rodrigoads.mymovies.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigoads.mymovies.domain.model.WatchLater
import java.text.FieldPosition

@Entity(tableName = "watch_later")
data class WatchLaterEntity(
    @PrimaryKey(autoGenerate = true)
    val position: Int = 0,
    val id: Int,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?,
    val formattedDate: String?,
)

fun WatchLaterEntity.toWatchLater(): WatchLater {
    return WatchLater(
        position = this.position,
        id = this.id,
        title = this.title,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        formattedDate = this.formattedDate
    )
}
