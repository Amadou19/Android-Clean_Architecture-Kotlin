package com.amadoutirera.presentation.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amadoutirera.domain.interactor.GetAlbums
import com.amadoutirera.domain.model.Album
import com.amadoutirera.presentation.GetAlbumViewModel
import com.amadoutirera.presentation.mapper.AlbumViewMapper
import com.amadoutirera.presentation.model.AlbumView
import com.amadoutirera.presentation.state.ResourceState
import com.amadoutirera.presentation.test.factory.AlbumFactory
import com.amadoutirera.presentation.test.factory.DataFactory
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetAlbumViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getAlbum = mock<GetAlbums>()
    private var albumMapper = mock<AlbumViewMapper>()
    var albumViewModel = GetAlbumViewModel(getAlbum, albumMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Album>>>()




    @Test
    fun fetchAlbumsExecutesUseCase() {
        albumViewModel.fetchAlbum()

        verify(getAlbum, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchAlbumsReturnsSuccess() {
        val albums = AlbumFactory.makeAlbumList(2)
        val albumViews = AlbumFactory.makeAlbumViewList(2)
        stubAlbumMapperMapToView(albumViews[0], albums[0])
        stubAlbumMapperMapToView(albumViews[1], albums[1])

        albumViewModel.fetchAlbum()

        verify(getAlbum).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(albums)
        assertEquals(ResourceState.SUCCESS, albumViewModel.getAlbumsState().value?.status)
    }


    @Test
    fun fetchAlbumsReturnsData() {
        val albums = AlbumFactory.makeAlbumList(2)
        val albumViews = AlbumFactory.makeAlbumViewList(2)
        stubAlbumMapperMapToView(albumViews[0], albums[0])
        stubAlbumMapperMapToView(albumViews[1], albums[1])

        albumViewModel.fetchAlbum()

        verify(getAlbum).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(albums)
        assertEquals(albumViews, albumViewModel.getAlbumsState().value?.data)
    }

    @Test
    fun fetchAlbumsReturnsError() {
        albumViewModel.fetchAlbum()

        verify(getAlbum).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())
        assertEquals(ResourceState.ERROR, albumViewModel.getAlbumsState().value?.status)
    }

    @Test
    fun fetchAlbumsReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        albumViewModel.fetchAlbum()

        verify(getAlbum).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))
        assertEquals(errorMessage, albumViewModel.getAlbumsState().value?.message)
    }

    private fun stubAlbumMapperMapToView(albumView: AlbumView, album: Album) {
        whenever(albumMapper.mapToView(album))
            .thenReturn(albumView)
    }

}