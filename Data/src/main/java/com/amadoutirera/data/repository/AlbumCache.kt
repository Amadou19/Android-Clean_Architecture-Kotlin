package com.amadoutirera.data.repository

import com.amadoutirera.data.model.AlbumEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface AlbumCache {

    fun clearAlbums(): Completable

    fun saveAlbums(albumEntity: List<AlbumEntity>): Completable

    fun getAlbums():Observable<List<AlbumEntity>>

    fun areAlbumCached(): Single<Boolean>

    fun setLastCacheTime(lastCache: Long): Completable

    fun isAlbumCacheExpired(): Single<Boolean>

}