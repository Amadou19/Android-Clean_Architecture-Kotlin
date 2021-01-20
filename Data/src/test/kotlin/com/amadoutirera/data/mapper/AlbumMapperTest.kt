package com.amadoutirera.data.mapper

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.domain.model.Album
import com.amadoutirera.data.factory.AlbumFactory
import org.junit.Test
import kotlin.test.assertEquals

class AlbumMapperTest {
    private val mapper = AlbumMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = AlbumFactory.makeAlbumsEntity()
        val model = mapper.mapFromEntity(entity)
        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = AlbumFactory.makeAlbums()
        val entity = mapper.mapToEntity(model)
        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: AlbumEntity, model: Album) {
        assertEquals(entity.albumId, model.albumId)
        assertEquals(entity.id, model.id)
        assertEquals(entity.title, model.title)
        assertEquals(entity.url, model.url)
        assertEquals(entity.thumbnailUrl, model.thumbnailUrl)
    }

}