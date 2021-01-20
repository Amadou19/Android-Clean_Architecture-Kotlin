package com.amadoutirera.mobile_ui.mapper


import com.amadoutirera.mobile_ui.album.AlbumList.AlbumUiMapper
import com.amadoutirera.mobile_ui.factory.AlbumFactory
import org.junit.Test
import kotlin.test.assertEquals

class AlbumMapperTest {
    private val mapper = AlbumUiMapper()


    @Test
    fun mapFromEntityMapsData() {
        val albumView = AlbumFactory.makeAlbumsView()
        val albumUi = mapper.mapToUi(albumView)

        assertEquals(albumView.albumId, albumUi.albumId)
        assertEquals(albumView.id, albumUi.id)
        assertEquals(albumView.title, albumUi.title)
        assertEquals(albumView.url, albumUi.url)
        assertEquals(albumView.thumbnailUrl, albumUi.thumbnailUrl)
    }


}