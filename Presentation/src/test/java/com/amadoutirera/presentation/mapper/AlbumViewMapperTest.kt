package com.amadoutirera.presentation.mapper

import com.amadoutirera.presentation.test.factory.AlbumFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AlbumViewMapperTest {
    private val mapper = AlbumViewMapper()


    @Test
    fun mapToEntityMapsData() {
        val model = AlbumFactory.makeAlbum()
        val entity = mapper.mapToView(model)

        assertEquals(entity.albumId, model.albumId)
        assertEquals(entity.id, model.id)
        assertEquals(entity.title, model.title)
        assertEquals(entity.url, model.url)
        assertEquals(entity.thumbnailUrl, model.thumbnailUrl)
    }


}