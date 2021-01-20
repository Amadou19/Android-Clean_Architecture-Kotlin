package com.amadoutirera.data.store

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumCache
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.amadoutirera.data.factory.AlbumFactory
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test

class AlbumCacheDataStoreTest {
    private val cache = mock<AlbumCache>()
    private val store = AlbumCacheDataStore(cache)



    @Test
    fun getAlbumsCompletes() {
        stubAlbumsCacheGetAlbums(Observable.just(listOf(AlbumFactory.makeAlbumsEntity())))
        val testObserver = store.getAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val data = listOf(AlbumFactory.makeAlbumsEntity())
        stubAlbumsCacheGetAlbums(Observable.just(data))
        val testObserver = store.getAlbums().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getAlbumsCallsCacheSource() {
        stubAlbumsCacheGetAlbums(Observable.just(listOf(AlbumFactory.makeAlbumsEntity())))
        store.getAlbums().test()
        verify(cache).getAlbums()
    }

    @Test
    fun saveAlbumsCompletes() {
        stubAlbumsCacheSaveAlbums(Completable.complete())
        val testObserver = store.saveAlbums(listOf(AlbumFactory.makeAlbumsEntity())).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveAlbumsCallsCacheStore() {
        val data = listOf(AlbumFactory.makeAlbumsEntity())
        stubAlbumsCacheSaveAlbums(Completable.complete())
        stubAlbumsCacheSetLastCacheTime(Completable.complete())
        store.saveAlbums(data).test()
        verify(cache).saveAlbums(data)
    }

    @Test
    fun clearAlbumsCompletes() {
        stubAlbumsClearAlbums(Completable.complete())
        val testObserver = store.clearAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearAlbumsCallCacheStore() {
        stubAlbumsClearAlbums(Completable.complete())
        store.clearAlbums().test()
        verify(cache).clearAlbums()
    }


    private fun stubAlbumsCacheGetAlbums(observable: Observable<List<AlbumEntity>>) {
        whenever(cache.getAlbums())
                .thenReturn(observable)
    }

    private fun stubAlbumsCacheSaveAlbums(completable: Completable) {
        stubAlbumsCacheSetLastCacheTime(completable)
        whenever(cache.saveAlbums(any()))
                .thenReturn(completable)
    }

    private fun stubAlbumsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
                .thenReturn(completable)
    }

    private fun stubAlbumsClearAlbums(completable: Completable) {
        whenever(cache.clearAlbums())
                .thenReturn(completable)
    }
}