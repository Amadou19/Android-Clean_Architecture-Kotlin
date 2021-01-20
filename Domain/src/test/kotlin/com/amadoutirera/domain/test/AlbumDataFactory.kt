package com.amadoutirera.domain.test

import com.amadoutirera.domain.model.Album
import java.util.*
import java.util.concurrent.ThreadLocalRandom

object AlbumDataFactory {


    private fun randomString(): String{
        return UUID.randomUUID().toString()
    }

    private fun randomInt(): Int{
        return ThreadLocalRandom.current().nextInt(0, 1000 + 1)
    }


    private fun makeAlbums(): Album{
        return Album(
                albumId = randomInt(),
                id = randomInt(),
                title = randomString(),
                url = randomString(),
                thumbnailUrl = randomString()
        )
    }

    fun makeAlbumsList(count: Int): List<Album> {
        val albums = mutableListOf<Album>()
        repeat(count){
            albums.add(makeAlbums())
        }
        return  albums
    }
}