package com.amadoutirera.data.store

import com.amadoutirera.data.repository.AlbumCache
import com.amadoutirera.data.repository.AlbumRemote
import com.amadoutirera.data.store.AlbumCacheDataStore
import com.amadoutirera.data.store.AlbumDataStoreFactory
import com.amadoutirera.data.store.AlbumRemoteDataStore
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import kotlin.test.assertEquals

class AlbumDataStoreFactoryTest {
    private val cache = mock<AlbumCache>()
    private val remote = mock<AlbumRemote>()
    private val cacheStore = AlbumCacheDataStore(cache)
    private val remoteStore = AlbumRemoteDataStore(remote)
    private val factory = AlbumDataStoreFactory(cacheStore, remoteStore)


    @Test
    fun `get data store returns remoteStore when cache expired`(){
        assertEquals(remoteStore, factory.getDataStore(albumCached = true, cacheExpired = true))
    }

    @Test
    fun `get data store returns remoteStore when albums not cached`() {
        assertEquals(remoteStore, factory.getDataStore(albumCached = false, cacheExpired = false))
    }


    @Test
    fun `get dataStore returns cacheStore`() {
        assertEquals(cacheStore, factory.getDataStore(albumCached = true, cacheExpired = false))
    }

    @Test
    fun `get cacheDataStore returns cacheStore`() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }

}