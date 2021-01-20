package com.amadoutirera.data.store

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumCache
import com.amadoutirera.data.repository.AlbumDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class AlbumCacheDataStore @Inject constructor( private val albumCache: AlbumCache) : AlbumDataStore {

    override fun getAlbums(): Observable<List<AlbumEntity>> {
        return albumCache.getAlbums()
    }

    override fun saveAlbums(listAlbumEntity: List<AlbumEntity>): Completable {
        return albumCache.saveAlbums(listAlbumEntity)
                .andThen(albumCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearAlbums(): Completable {
        return albumCache.clearAlbums()
    }


}