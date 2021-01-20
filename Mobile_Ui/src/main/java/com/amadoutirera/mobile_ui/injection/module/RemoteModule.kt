package com.amadoutirera.mobile_ui.injection.module

import com.amadoutirera.data.repository.AlbumRemote
import com.amadoutirera.mobile_ui.BuildConfig
import com.amadoutirera.remote.AlbumRemoteImpl
import com.amadoutirera.remote.service.AlbumService
import com.amadoutirera.remote.service.AlbumServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun provideAlbumService(): AlbumService{
            return AlbumServiceFactory.makeAlbumService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindAlbumRemote(albumRemoteImpl: AlbumRemoteImpl): AlbumRemote
}