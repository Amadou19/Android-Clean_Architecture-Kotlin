package com.amadoutirera.cache.mapper

import com.amadoutirera.cache.model.CachedAlbum
import com.amadoutirera.data.model.AlbumEntity
import javax.inject.Inject

open class CachedAlbumMapper @Inject constructor(): CacheMapper<CachedAlbum, AlbumEntity> {

    override fun mapFromCached(cache: CachedAlbum): AlbumEntity {
        return AlbumEntity(albumId = cache.albumId,id = cache.id, title = cache.title, url = cache.url, thumbnailUrl = cache.thumbnailUrl)
    }

    override fun mapToCached(entity: AlbumEntity): CachedAlbum {
        return CachedAlbum(albumId = entity.albumId, id = entity.id, title = entity.title, url = entity.url, thumbnailUrl = entity.thumbnailUrl)
    }

}