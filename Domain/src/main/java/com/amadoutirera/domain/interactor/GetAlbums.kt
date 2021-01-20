package com.amadoutirera.domain.interactor

import com.amadoutirera.domain.executor.PostExecutionThread
import com.amadoutirera.domain.model.Album
import com.amadoutirera.domain.repository.AlbumsRepository
import com.amadoutirera.domain.usecase.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

open class GetAlbums @Inject constructor(
    private val albumsRepository: AlbumsRepository,
    postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<Album>, Nothing?>(postExecutionThread){


    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Album>> {
        return albumsRepository.getAlbums()
    }

}