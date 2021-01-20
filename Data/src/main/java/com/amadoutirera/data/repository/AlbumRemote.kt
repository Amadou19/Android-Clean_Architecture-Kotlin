package com.amadoutirera.data.repository

import com.amadoutirera.data.model.AlbumEntity
import io.reactivex.Observable

interface AlbumRemote {

    fun getAlbums(): Observable<List<AlbumEntity>>

}