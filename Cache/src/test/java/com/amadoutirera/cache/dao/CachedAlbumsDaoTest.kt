package com.amadoutirera.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.amadoutirera.cache.db.AlbumDatabase
import com.amadoutirera.cache.test.factory.AlbumDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
class CachedAlbumsDaoTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            AlbumDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }



    @Test
    fun getAlbumsReturnsData() {
        val albums = AlbumDataFactory.makeCachedAlbums()
        database.cachedAlbumDao().insertAlbums(listOf(albums))

        val testObserver = database.cachedAlbumDao().getAlbums().test()
        testObserver.assertValue(listOf(albums))
    }

    @Test
    fun deleteAlbumsClearsData() {
        val albums = AlbumDataFactory.makeCachedAlbums()
        database.cachedAlbumDao().insertAlbums(listOf(albums))
        database.cachedAlbumDao().deleteAllAlbums()

        val testObserver = database.cachedAlbumDao().getAlbums().test()
        testObserver.assertValue(emptyList())
    }
}