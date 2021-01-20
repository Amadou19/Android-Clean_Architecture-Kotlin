package com.amadoutirera.cache.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amadoutirera.cache.dao.CacheAlbumDao
import com.amadoutirera.cache.dao.ConfigDao
import com.amadoutirera.cache.model.CachedAlbum
import com.amadoutirera.cache.model.Config
import javax.inject.Inject


@Database(entities = [CachedAlbum::class, Config::class], version = 1)
abstract class AlbumDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedAlbumDao(): CacheAlbumDao
    abstract fun configDao(): ConfigDao

    companion object {
        private var INSTANCE: AlbumDatabase? = null
        private val lock = Any()
        fun getInstance(context: Context): AlbumDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AlbumDatabase::class.java, "albums.db")
                            .build()
                    }
                    return INSTANCE as AlbumDatabase
                }
            }
            return INSTANCE as AlbumDatabase
        }
    }

}