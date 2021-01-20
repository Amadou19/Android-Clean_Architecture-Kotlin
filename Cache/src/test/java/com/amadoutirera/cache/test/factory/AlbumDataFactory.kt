package com.amadoutirera.cache.test.factory

import com.amadoutirera.cache.model.CachedAlbum
import com.amadoutirera.data.model.AlbumEntity

object AlbumDataFactory {


    fun makeCachedAlbums(): CachedAlbum {
        return CachedAlbum(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }


    fun makeAlbumsEntity(): AlbumEntity {
        return AlbumEntity(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }



}