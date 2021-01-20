package com.amadoutirera.remote.mapper

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.remote.model.AlbumModel
import javax.inject.Inject

open class AlbumResponseModelMapper @Inject constructor(): ModelMapper<AlbumModel, AlbumEntity> {

    override fun mapFromModel(model: AlbumModel): AlbumEntity {
        return  AlbumEntity(albumId = model.albumId,id = model.id, title = model.title, url = model.url, thumbnailUrl = model.thumbnailUrl)
    }


}