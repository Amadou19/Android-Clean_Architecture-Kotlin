package com.amadoutirera.data

import com.amadoutirera.data.mapper.AlbumMapper
import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumCache
import com.amadoutirera.data.repository.AlbumDataStore
import com.amadoutirera.data.store.AlbumDataStoreFactory
import com.amadoutirera.domain.model.Album
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.amadoutirera.data.factory.AlbumFactory
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test


class AlbumDataRepositoryTest {
    private val mapper = mock<AlbumMapper>()
    private val factory = mock<AlbumDataStoreFactory>()
    private val store = mock<AlbumDataStore>()
    private val cache = mock<AlbumCache>()

    private val repository = AlbumDataRepository(mapper, cache, factory)



    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubAreAlbumsCached(Single.just(false))
        stubSaveAlbums(Completable.complete())
    }

    @Test
    fun getAlbumsCompletes() {
        stubGetAlbums(Observable.just(listOf(AlbumFactory.makeAlbumsEntity())))
        stubMapper(AlbumFactory.makeAlbums(), any())

        val testObserver = repository.getAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val albumsEntity = AlbumFactory.makeAlbumsEntity()
        val albums = AlbumFactory.makeAlbums()
        stubGetAlbums(Observable.just(listOf(albumsEntity)))
        stubMapper(albums, albumsEntity)

        val testObserver = repository.getAlbums().test()
        testObserver.assertValue(listOf(albums))
    }



    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isAlbumCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreAlbumsCached(single: Single<Boolean>) {
        whenever(cache.areAlbumCached())
            .thenReturn(single)
    }

    private fun stubMapper(model: Album, entity: AlbumEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubGetAlbums(observable: Observable<List<AlbumEntity>>) {
        whenever(store.getAlbums())
            .thenReturn(observable)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any()))
            .thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
            //.thenReturn(store)
    }

    private fun stubSaveAlbums(completable: Completable) {
        whenever(store.saveAlbums(any()))
            .thenReturn(completable)
    }

}