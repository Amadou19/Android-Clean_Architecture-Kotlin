package com.amadoutirera.mobile_ui.factory

import com.amadoutirera.domain.model.Album
import com.amadoutirera.mobile_ui.album.model.AlbumUi
import com.amadoutirera.presentation.model.AlbumView

object AlbumFactory {



    fun makeAlbumUi(): AlbumUi {
        return AlbumUi(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }


    fun makeAlbumsView(): AlbumView {
        return AlbumView(
                albumId = DataFactory.randomInt(),
                id = DataFactory.randomInt(),
                title = DataFactory.stringValue(),
                url = DataFactory.stringValue(),
                thumbnailUrl = DataFactory.stringValue()
        )
    }

}