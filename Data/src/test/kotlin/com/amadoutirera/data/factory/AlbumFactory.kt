package com.amadoutirera.data.factory

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.domain.model.Album

object AlbumFactory {



    fun makeAlbumsEntity(): AlbumEntity {
        return AlbumEntity(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }


    fun makeAlbums(): Album {
        return Album(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }

}