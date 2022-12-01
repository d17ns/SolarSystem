package com.d17ns.solarsystem

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.d17ns.solarsystem.network.Planet
import com.d17ns.solarsystem.ui.PlanetApiStatus
import com.d17ns.solarsystem.ui.PlanetListAdapter

// update data yang ditampilkan pada RecyclerView
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Planet>?) {
    val adapter = recyclerView.adapter as PlanetListAdapter
    adapter.submitList(data)
}

// menggunakan library Coil untuk me-load image menggunakan URL ke dalam ImageView
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_connection_error)
        }
    }
}

/*
 * binding adapter untuk menampilkan nilai status dari class PlanetApiStatus
 * nilai status tersebut ditampilkan dalam bentuk gambar saat user melakukan request
 * PlanetApiStatus.LOADING --> menampilkan loading_animation
 * PlanetApiStatus.DONE --> gambar status dihilangkan
 * PlanetApiStatus.ERROR --> menampilkan ic_connection_error
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: PlanetApiStatus?) {
    when(status) {
        PlanetApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        PlanetApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        PlanetApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}

