package com.amadoutirera.data

import com.amadoutirera.data.mapper.AlbumMapper
import com.amadoutirera.data.repository.AlbumCache
import com.amadoutirera.data.store.AlbumDataStoreFactory
import com.amadoutirera.domain.model.Album
import com.amadoutirera.domain.repository.AlbumsRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

open class AlbumDataRepository @Inject constructor(
    private val mapper: AlbumMapper,
    private val cache: AlbumCache,
    private val factory: AlbumDataStoreFactory)
    : AlbumsRepository{


    override fun getAlbums(): Observable<List<Album>> {
        return Observable.zip(
            cache.areAlbumCached().toObservable(),
            cache.isAlbumCacheExpired().toObservable(),
            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> {
                    areCached, isExpired -> Pair(areCached, isExpired) }
        )
            .flatMap {
                factory.getDataStore(it.first, it.second).getAlbums()
            }
            .flatMap { listAlbum ->
                factory.getCacheDataStore()
                    .saveAlbums(listAlbum)
                    .andThen(Observable.just(listAlbum))
            }
            .map { listAlbum ->
                listAlbum.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

}