package com.amadoutirera.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.amadoutirera.cache.db.AlbumDatabase
import com.amadoutirera.cache.mapper.CachedAlbumMapper
import com.amadoutirera.cache.test.factory.AlbumDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest= Config.NONE)
class AlbumCacheImplTest {
    @Rule
    @JvmField
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



    private val entityMapper = CachedAlbumMapper()
    private val cache = AlbumCacheImpl(entityMapper, database)

    @Test
    fun clearTablesCompletes() {
        val testObserver = cache.clearAlbums().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveAlbumsCompletes() {
        val albums = listOf(AlbumDataFactory.makeAlbumsEntity())

        val testObserver = cache.saveAlbums(albums).test()
        testObserver.assertComplete()
    }

    @Test
    fun getAlbumsReturnsData() {
        val albums = listOf(AlbumDataFactory.makeAlbumsEntity())
        cache.saveAlbums(albums).test()

        val testObserver = cache.getAlbums().test()
        testObserver.assertValue(albums)
    }


    @Test
    fun areAlbumsCacheReturnsData() {
        val albums = listOf(AlbumDataFactory.makeAlbumsEntity())
        cache.saveAlbums(albums).test()

        val testObserver = cache.areAlbumCached().test()
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        val testObserver = cache.setLastCacheTime(1000L).test()
        testObserver.assertComplete()
    }

    @Test
    fun isAlbumsCacheExpiredReturnsExpired() {
        val testObserver = cache.isAlbumCacheExpired().test()
        testObserver.assertValue(true)
    }

    @Test
    fun isAlbumsCacheExpiredReturnsNotExpired() {
        cache.setLastCacheTime(System.currentTimeMillis() - 1000).test()
        val testObserver = cache.isAlbumCacheExpired().test()
        testObserver.assertValue(false)
    }


}