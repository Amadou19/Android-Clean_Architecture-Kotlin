package com.amadoutirera.data.store

import com.amadoutirera.data.repository.AlbumDataStore
import javax.inject.Inject

open class AlbumDataStoreFactory @Inject constructor(
    private val albumCacheDataStore: AlbumCacheDataStore,
    private val albumRemoteDataStore: AlbumRemoteDataStore) {

    fun getDataStore(albumCached: Boolean, cacheExpired: Boolean): AlbumDataStore{
        return if (albumCached && !cacheExpired){
            albumCacheDataStore
        }
        else{
            albumRemoteDataStore
        }
    }

    open fun getCacheDataStore(): AlbumCacheDataStore{
        return albumCacheDataStore
    }
}