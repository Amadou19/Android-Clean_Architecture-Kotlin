package com.amadoutirera.remote.service

import com.amadoutirera.remote.model.AlbumModel
import io.reactivex.Observable
import retrofit2.http.GET

interface AlbumService {

    @GET("img/shared/technical-test.json")
    fun getAlbums():Observable<List<AlbumModel>>
}