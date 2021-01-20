package com.amadoutirera.domain.repository

import com.amadoutirera.domain.model.Album
import io.reactivex.Observable

interface AlbumsRepository {
    fun getAlbums(): Observable<List<Album>>
}