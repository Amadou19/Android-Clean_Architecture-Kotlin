package com.amadoutirera.remote

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumRemote
import com.amadoutirera.remote.mapper.AlbumResponseModelMapper
import com.amadoutirera.remote.service.AlbumService
import io.reactivex.Observable
import javax.inject.Inject

class AlbumRemoteImpl @Inject constructor(private val service: AlbumService, private val albumResponseModelMapper: AlbumResponseModelMapper): AlbumRemote{

    override fun getAlbums(): Observable<List<AlbumEntity>> {
        return service.getAlbums().map {
            it.map { albumModel ->
                albumResponseModelMapper.mapFromModel(albumModel) }
        }
    }

}