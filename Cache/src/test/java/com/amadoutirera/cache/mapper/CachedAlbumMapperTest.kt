package com.amadoutirera.cache.mapper

import com.amadoutirera.cache.model.CachedAlbum
import com.amadoutirera.cache.test.factory.AlbumDataFactory
import com.amadoutirera.data.model.AlbumEntity
import org.junit.Test
import kotlin.test.assertEquals

class CachedAlbumMapperTest {
    private val mapper = CachedAlbumMapper()


    @Test
    fun mapFromCachedMapsData() {
        val model = AlbumDataFactory.makeCachedAlbums()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = AlbumDataFactory.makeAlbumsEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }


    private fun assertEqualData(entity: CachedAlbum, model: AlbumEntity) {
        assertEquals(entity.albumId, model.albumId)
        assertEquals(entity.id, model.id)
        assertEquals(entity.title, model.title)
        assertEquals(entity.url, model.url)
        assertEquals(entity.thumbnailUrl, model.thumbnailUrl)
    }
}