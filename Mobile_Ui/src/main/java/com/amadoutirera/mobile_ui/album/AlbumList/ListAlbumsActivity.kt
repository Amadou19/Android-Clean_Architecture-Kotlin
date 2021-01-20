package com.amadoutirera.mobile_ui.album.AlbumList

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.amadoutirera.mobile_ui.R
import com.amadoutirera.mobile_ui.album.model.AlbumUi
import com.amadoutirera.mobile_ui.injection.ViewModelFactory
import com.amadoutirera.presentation.GetAlbumViewModel
import com.amadoutirera.presentation.model.AlbumView
import com.amadoutirera.presentation.state.Resource
import com.amadoutirera.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_list_albums.*
import timber.log.Timber
import javax.inject.Inject

class ListAlbumsActivity : AppCompatActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: AlbumUiMapper
    @Inject lateinit var albumAdapter: AlbumsAdapter
    private lateinit var getAlbumViewModel: GetAlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_albums)
        AndroidInjection.inject(this)
        setSupportActionBar(findViewById(R.id.toolbar))


        getAlbumViewModel = ViewModelProviders.of(this, viewModelFactory).get(GetAlbumViewModel::class.java)
        getAlbumViewModel.getAlbumsState().observe(this, Observer { handleDataState(it)})

        albumAdapter.albumListener = albumListener
        recyclerView_Albums.layoutManager = GridLayoutManager(this,2)
        recyclerView_Albums.adapter = albumAdapter

        button_try_again.setOnClickListener {
            getAlbumViewModel.fetchAlbum()
        }


    }


    private fun handleDataState(resource: Resource<List<AlbumView>, String>) {

        when (resource.status){

            ResourceState.LOADING -> loading()

            ResourceState.SUCCESS -> {
                resource.data?.map { mapper.mapToUi(it) }.let { listAlbums ->
                    success(listAlbums!!)
                }
            }

            ResourceState.ERROR -> error(resource.message!!)
        }
    }

    private fun loading(){
        progress.visibility = View.VISIBLE
        tv_error.visibility = View.GONE
        button_try_again.visibility = View.GONE
    }

    private fun success(listAlbums: List<AlbumUi>){
        albumAdapter.listAlbums = listAlbums
        albumAdapter.notifyDataSetChanged()

        progress.visibility = View.GONE
        tv_error.visibility = View.GONE
        button_try_again.visibility = View.GONE

        Timber.d("ResourceState.SUCCESS")
    }
    private fun error(message: String){
        Timber.e(message)
        progress.visibility = View.GONE
        tv_error.visibility = View.VISIBLE
        button_try_again.visibility = View.VISIBLE
    }


    private val albumListener = object: AlbumListener{
        override fun onAlbumClicked(albumId: Int) {
        }
    }

}