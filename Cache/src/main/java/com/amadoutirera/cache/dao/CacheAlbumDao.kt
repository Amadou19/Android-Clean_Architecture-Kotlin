package com.amadoutirera.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amadoutirera.cache.db.AlbumConstants.DELETE_ALL_ALBUMS_QUERY
import com.amadoutirera.cache.db.AlbumConstants.GET_ALBUMS_QUERY
import com.amadoutirera.cache.model.CachedAlbum
import io.reactivex.Flowable


@Dao
abstract class CacheAlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAlbums(album: List<CachedAlbum>)

    @Query(GET_ALBUMS_QUERY)
    abstract fun getAlbums(): Flowable<List<CachedAlbum>>

    @Query(DELETE_ALL_ALBUMS_QUERY)
    abstract fun deleteAllAlbums()

}