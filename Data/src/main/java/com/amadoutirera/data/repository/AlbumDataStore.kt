package com.amadoutirera.data.repository

import com.amadoutirera.data.model.AlbumEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface AlbumDataStore {

    fun getAlbums(): Observable<List<AlbumEntity>>

    fun saveAlbums(listAlbumEntity: List<AlbumEntity>): Completable

    fun clearAlbums(): Completable
}



