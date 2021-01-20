package com.amadoutirera.data.mapper

import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.domain.model.Album
import javax.inject.Inject

open class AlbumMapper @Inject constructor(): EntityMapper<AlbumEntity, Album> {

    override fun mapFromEntity(entity: AlbumEntity): Album {
        return Album(
            albumId = entity.albumId,
            id = entity.id,
            title = entity.title,
            url = entity.thumbnailUrl,
            thumbnailUrl = entity.thumbnailUrl
        )
    }

    override fun mapToEntity(domain: Album): AlbumEntity {
        return AlbumEntity(
            albumId = domain.albumId,
            id = domain.id,
            title = domain.title,
            url = domain.thumbnailUrl,
            thumbnailUrl = domain.thumbnailUrl
        )
    }

}
