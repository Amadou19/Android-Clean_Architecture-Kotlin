package com.amadoutirera.mobile_ui.injection.module

import android.app.Application
import com.amadoutirera.cache.AlbumCacheImpl
import com.amadoutirera.cache.db.AlbumDatabase
import com.amadoutirera.data.repository.AlbumCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object{
        @Provides
        @JvmStatic
        fun providesDatabase(application: Application): AlbumDatabase{
            return AlbumDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindAlbumCache(albumCacheImpl: AlbumCacheImpl): AlbumCache
}