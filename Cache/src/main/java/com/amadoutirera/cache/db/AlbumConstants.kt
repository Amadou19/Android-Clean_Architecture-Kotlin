package com.amadoutirera.cache.db

object AlbumConstants {
    const val TABLE_NAME = "albums"

    const val GET_ALBUMS_QUERY = "SELECT * FROM $TABLE_NAME"

    const val  DELETE_ALL_ALBUMS_QUERY = "DELETE FROM $TABLE_NAME"
}