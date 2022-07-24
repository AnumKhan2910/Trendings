package com.example.gittrendings.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gittrendings.data.TrendingUIData
import com.example.gittrendings.presentation.TrendingDataAdapter

@BindingAdapter(value = ["toggle_visibility"])
fun View.toggleVisibility(show: Boolean) {
    updateViewVisibility(show)
}

@BindingAdapter(value = ["load_image"])
fun ImageView.loadImage(uri: String?) {
    uri?.let {
        Glide.with(context)
            .asBitmap()
            .load(it)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(this)
    }
}

@BindingAdapter("adapter")
fun <T : RecyclerView.ViewHolder>
    RecyclerView.bindAdapter(adapter: RecyclerView.Adapter<T>) {
    this.adapter = adapter
}

@BindingAdapter(value = ["trending_data"])
fun RecyclerView.setList(data: List<TrendingUIData>?) {
    val items = data.orEmpty()
    if (items.isEmpty()) hide() else {
        (adapter as? TrendingDataAdapter)?.submitList(items)
        show()
    }
}
