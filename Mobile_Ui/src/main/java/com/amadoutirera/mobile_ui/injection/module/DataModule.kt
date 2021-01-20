package com.amadoutirera.mobile_ui.injection.module

import com.amadoutirera.data.AlbumDataRepository
import com.amadoutirera.domain.repository.AlbumsRepository
import dagger.Binds
import dagger.Module


@Module
abstract class DataModule {
    @Binds
    abstract fun bindDataRepository(dataRepository: AlbumDataRepository): AlbumsRepository
}