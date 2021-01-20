package com.amadoutirera.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amadoutirera.cache.db.AlbumConstants

@Entity(tableName = AlbumConstants.TABLE_NAME)
data class CachedAlbum (
    val albumId: Int,
    @PrimaryKey
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
    )