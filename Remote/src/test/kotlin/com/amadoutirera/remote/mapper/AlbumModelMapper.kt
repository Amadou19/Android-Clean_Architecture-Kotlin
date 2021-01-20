package com.amadoutirera.remote.mapper

import com.amadoutirera.remote.test.factory.AlbumDataFactory
import org.junit.Test
import kotlin.test.assertEquals

class AlbumModelMapper {
    private val mapper = AlbumResponseModelMapper()

    @Test
    fun mapFromModelMapsData() {
        val model = AlbumDataFactory.makeAlbums()
        val entity = mapper.mapFromModel(model)

        assertEquals(model.albumId, entity.albumId)
        assertEquals(model.id, entity.id)
        assertEquals(model.title, entity.title)
        assertEquals(model.url, entity.url)
        assertEquals(model.thumbnailUrl, entity.thumbnailUrl)
    }

}