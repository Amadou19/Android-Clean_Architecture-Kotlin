package com.amadoutirera.mobile_ui.album.AlbumList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amadoutirera.mobile_ui.R
import com.amadoutirera.mobile_ui.album.model.AlbumUi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_items.view.*
import java.util.*
import javax.inject.Inject

class AlbumsAdapter @Inject constructor(): RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {
    var listAlbums: List<AlbumUi> = listOf()
    var albumListener: AlbumListener? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_items, parent, false)
        return ViewHolder(itemView)
    }


    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val album = listAlbums[position]

        holder.albumGroup.text = album.albumId.toString()
        holder.albumTitle.text = album.title

        Picasso.get ()
            .load (album.thumbnailUrl)
            .resize (150, 150)
            .centerCrop ()
            .into (holder.albumImage)

        holder.itemView.setOnClickListener {
            albumListener?.onAlbumClicked(album.id)
        }

    }


    override fun getItemCount(): Int {
        return listAlbums.count()
    }



    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var albumImage: ImageView = view.album_image
        val albumTitle: TextView = view.album_title
        val albumGroup: TextView = view.album_group

    }

}