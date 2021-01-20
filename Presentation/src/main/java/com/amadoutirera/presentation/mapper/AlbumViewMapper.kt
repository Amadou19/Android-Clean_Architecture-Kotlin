package com.amadoutirera.presentation.mapper

import com.amadoutirera.domain.model.Album
import com.amadoutirera.presentation.model.AlbumView
import javax.inject.Inject

open class AlbumViewMapper @Inject constructor(): Mapper<AlbumView, Album>{

    override fun mapToView(domain: Album): AlbumView {
        return AlbumView(albumId = domain.albumId,id = domain.id, title = domain.title, url = domain.url, thumbnailUrl = domain.thumbnailUrl)
    }


}