package com.amadoutirera.remote.test.factory

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.remote.model.AlbumModel
import java.util.*

object AlbumDataFactory {


    fun makeAlbumsEntity(): AlbumEntity {
        return AlbumEntity(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }


    fun makeAlbums(): AlbumModel {
        return AlbumModel(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }

    fun makeAlbumsResponse(): List<AlbumModel> {
        return (listOf(makeAlbums(), makeAlbums()))
    }
}