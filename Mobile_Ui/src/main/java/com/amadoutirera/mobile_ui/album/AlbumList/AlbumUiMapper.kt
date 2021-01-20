package com.amadoutirera.mobile_ui.album.AlbumList

import com.amadoutirera.mobile_ui.mapper.UiMapper
import com.amadoutirera.mobile_ui.album.model.AlbumUi
import com.amadoutirera.presentation.model.AlbumView
import javax.inject.Inject

open class AlbumUiMapper @Inject constructor(): UiMapper<AlbumView, AlbumUi> {

    override fun mapToUi(presentation: AlbumView): AlbumUi {
        return AlbumUi(
            albumId = presentation.albumId,
            id = presentation.id,
            title = presentation.title,
            url = presentation.url,
            thumbnailUrl = presentation.thumbnailUrl
        )
    }

}