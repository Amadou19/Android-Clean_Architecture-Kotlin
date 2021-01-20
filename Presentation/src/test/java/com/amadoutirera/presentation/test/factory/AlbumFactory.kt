package com.amadoutirera.presentation.test.factory

import com.amadoutirera.domain.model.Album
import com.amadoutirera.presentation.model.AlbumView

object AlbumFactory {


    private fun makeAlbumsView(): AlbumView {
        return AlbumView(
            albumId = DataFactory.randomInt(),
            id = DataFactory.randomInt(),
            title = DataFactory.stringValue(),
            url = DataFactory.stringValue(),
            thumbnailUrl = DataFactory.stringValue()
        )
    }


    fun makeAlbum(): Album {
        return Album(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }



    fun makeAlbumViewList(count: Int): List<AlbumView> {
        val albums = mutableListOf<AlbumView>()
        repeat(count) {
            albums.add(makeAlbumsView())
        }
        return albums
    }

    fun makeAlbumList(count: Int): List<Album> {
        val albums = mutableListOf<Album>()
        repeat(count) {
            albums.add(makeAlbum())
        }
        return albums
    }


}