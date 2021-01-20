package com.amadoutirera.cache

import com.amadoutirera.cache.db.AlbumDatabase
import com.amadoutirera.cache.mapper.CachedAlbumMapper
import com.amadoutirera.cache.model.Config
import com.amadoutirera.data.model.AlbumEntity
import com.amadoutirera.data.repository.AlbumCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

open class AlbumCacheImpl @Inject constructor(private val mapper: CachedAlbumMapper, private val albumDatabase: AlbumDatabase): AlbumCache {


    override fun clearAlbums(): Completable {
        return  Completable.defer {
            albumDatabase.cachedAlbumDao().deleteAllAlbums()
            Completable.complete()
        }
    }

    override fun saveAlbums(albumEntity: List<AlbumEntity>): Completable {
        return Completable.defer {
            albumDatabase.cachedAlbumDao().insertAlbums(
                albumEntity.map {
                    mapper.mapToCached(it)  }
            )
            Completable.complete()
        }
    }

    override fun getAlbums(): Observable<List<AlbumEntity>> {
        return albumDatabase.cachedAlbumDao().getAlbums()
            .toObservable()
            .map {
                it.map { listCacheAlbum ->
                    mapper.mapFromCached(listCacheAlbum)
                }
            }
    }

    override fun areAlbumCached(): Single<Boolean> {
       return albumDatabase.cachedAlbumDao().getAlbums().isEmpty
           .map {
               !it
           }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            albumDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }

    }

    override fun isAlbumCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 10000).toLong()
        return albumDatabase.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }
}