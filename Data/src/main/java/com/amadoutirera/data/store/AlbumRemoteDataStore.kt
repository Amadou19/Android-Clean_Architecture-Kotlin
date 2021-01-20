package com.amadoutirera.data.store

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumDataStore
import com.amadoutirera.data.repository.AlbumRemote
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRemoteDataStore @Inject constructor(private  val albumRemote: AlbumRemote): AlbumDataStore {

    override fun getAlbums(): Observable<List<AlbumEntity>> {
        return  albumRemote.getAlbums()
    }

    override fun saveAlbums(listAlbumEntity: List<AlbumEntity>): Completable {
        throw UnsupportedOperationException("Save Albums ins't supported her")
    }

    override fun clearAlbums(): Completable {
        throw UnsupportedOperationException("Clears Albums ins't supported her")
    }
}