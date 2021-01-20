package com.amadoutirera.domain.interactor

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.model.Album
import com.amadoutirera.domain.repository.AlbumsRepository
import com.amadoutirera.domain.test.AlbumDataFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetAlbumsTest(){
    private lateinit var getAlbums: GetAlbums
    @Mock lateinit var albumsRepository: AlbumsRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread



    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        getAlbums = GetAlbums(albumsRepository, postExecutionThread)
    }

    @Test
    fun getAlbumsCompletes(){
        stubGetAlbums(Observable.just(AlbumDataFactory.makeAlbumsList(2)))
        val testObserver = getAlbums.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }


    @Test
    fun getAlbumsReturnsData(){
        val albums = AlbumDataFactory.makeAlbumsList(2)
        stubGetAlbums(Observable.just(albums))
        val testObserver = getAlbums.buildUseCaseObservable().test()
        testObserver.assertComplete()

    }

    private fun stubGetAlbums(observable: Observable<List<Album>>) {
        whenever(albumsRepository.getAlbums())
                .thenReturn(observable)
    }

}