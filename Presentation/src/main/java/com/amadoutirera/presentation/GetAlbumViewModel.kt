package com.amadoutirera.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amadoutirera.domain.interactor.GetAlbums
import com.amadoutirera.domain.model.Album
import com.amadoutirera.presentation.mapper.AlbumViewMapper
import com.amadoutirera.presentation.model.AlbumView
import com.amadoutirera.presentation.state.Resource
import com.amadoutirera.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class GetAlbumViewModel @Inject constructor(private val getAlbums: GetAlbums, private  val mapper: AlbumViewMapper): ViewModel() {
    private val liveData: MutableLiveData<Resource<List<AlbumView>, String>> = MutableLiveData()

    init {
        fetchAlbum()
    }


    fun getAlbumsState(): LiveData<Resource<List<AlbumView>, String>> {
        return  liveData
    }


    fun fetchAlbum(){
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getAlbums.execute(GetAlbumSubscriber())
    }


    private inner class GetAlbumSubscriber(): DisposableObserver<List<Album>>() {
        override fun onNext(listAlbum: List<Album>) {
            liveData.postValue(
                Resource(ResourceState.SUCCESS, listAlbum.map { mapper.mapToView(it)}, null)
            )
        }
        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
        override fun onComplete() {}
    }



    override fun onCleared() {
        getAlbums.dispose()
        super.onCleared()
    }

}