package com.amadoutirera.data.store

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumRemote
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.amadoutirera.data.factory.AlbumFactory
import io.reactivex.Observable
import org.junit.Test

class AlbumRemoteDataStoreTest {
    private val remote = mock<AlbumRemote>()
    private val store = AlbumRemoteDataStore(remote)


    @Test
    fun getAlbumsCompletes() {
        stubRemoteGetAlbums(Observable.just(listOf(AlbumFactory.makeAlbumsEntity())))
        val testObserver = store.getAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val data = listOf(AlbumFactory.makeAlbumsEntity())
        stubRemoteGetAlbums(Observable.just(data))
        val testObserver = store.getAlbums().test()
        testObserver.assertValue(data)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveAlbumsThrowsException() {
        store.saveAlbums(listOf()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearAlbumsThrowsException() {
        store.clearAlbums().test()
    }


    private fun stubRemoteGetAlbums(observable: Observable<List<AlbumEntity>>) {
        whenever(remote.getAlbums())
                .thenReturn(observable)
    }

}