package com.amadoutirera.remote

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.remote.mapper.AlbumResponseModelMapper
import com.amadoutirera.remote.model.AlbumModel
import com.amadoutirera.remote.service.AlbumService
import com.amadoutirera.remote.test.factory.AlbumDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

class AlbumRemoteImplTest {
    private val mapper = mock<AlbumResponseModelMapper>()
    private val service = mock<AlbumService>()
    private val remote = AlbumRemoteImpl(service, mapper)


    @Test
    fun getAlbumsCompletes() {
        stubAlbumServiceGetAlbums(
                Observable.just(AlbumDataFactory.makeAlbumsResponse())
        )

        val testObserver = remote.getAlbums().test()
        testObserver.assertComplete()
    }


@Test
fun getAlbumsCallsServer() {
    stubAlbumServiceGetAlbums(
            Observable.just(AlbumDataFactory.makeAlbumsResponse())
    )
    remote.getAlbums().test()
    verify(service).getAlbums()
}

    @Test
    fun getAlbumsReturnsData() {
        val response = AlbumDataFactory.makeAlbumsResponse()
        stubAlbumServiceGetAlbums(Observable.just(response))
        val entities = mutableListOf<AlbumEntity>()
        response.forEach {
            val entity = AlbumDataFactory.makeAlbumsEntity()
            entities.add(entity)
            stubAlbumsResponseModelMapperMapFromModel(it, entity)
        }
        val testObserver = remote.getAlbums().test()
        testObserver.assertValue(entities)
    }

    @Test
    fun getAlbumsCallsServerWithCorrectParameters() {
        stubAlbumServiceGetAlbums(
                Observable.just(AlbumDataFactory.makeAlbumsResponse())
        )
        remote.getAlbums().test()
        verify(service).getAlbums()
    }


    private fun stubAlbumServiceGetAlbums(observable: Observable<List<AlbumModel>>) {
        whenever(service.getAlbums())
                .thenReturn(observable)
    }

    private fun stubAlbumsResponseModelMapperMapFromModel(model: AlbumModel, entity: AlbumEntity) {
        whenever(mapper.mapFromModel(model))
                .thenReturn(entity)
    }

}