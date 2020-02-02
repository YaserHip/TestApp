package com.example.phunapp.utils

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.phunapp.PhunApp
import com.example.phunapp.R
import com.example.phunapp.data.models.ItemModel
import com.example.phunapp.view.adapters.mainfeed.FeedAdapter
import com.example.phunapp.view.ui.mainfeed.MainFeedViewModel

@BindingAdapter("app:onLoadingVisibility")
fun onLoadingVisible(view: View, isLoading: Boolean) {
    if (isLoading)
        view.visibility = View.VISIBLE
    else
        view.visibility = View.GONE
}

@BindingAdapter("app:setItems")
fun setItems(view: RecyclerView, resource: Resource<List<ItemModel>>?) {
    with(view.adapter as FeedAdapter) {
        resource?.data?.let { update(it) }
    }
}

@BindingAdapter(value = ["app:location1", "app:location2"], requireAll = true)
fun setLocationsText(view: TextView, location1: String, location2: String) {
    view.text = "$location1, $location2"
}

@BindingAdapter("app:onClickShare")
fun setOnClickAction(view: View, itemModel: ItemModel) {
    view.setOnClickListener {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, PhunApp.instance.getStringFromRes(R.string.app_name))
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "${itemModel.title}\n\n" +
                    "${itemModel.locationline1}, ${itemModel.locationline2}\n\n" +
                    "${itemModel.description}"
        )
        view.context.startActivity(Intent.createChooser(intent, "Share via"))
    }
}

@BindingAdapter("app:loadImageFromUrl")
fun setImageFromURL(view: ImageView, url: String?) {
    Glide.with(view.context).load(url)
        .apply(
            RequestOptions.circleCropTransform().placeholder(
                converDrawableToCircular(
                    view.context,
                    R.drawable.placeholder_nomoon
                )
            )
        )
        .into(view)
}

@BindingAdapter("app:onSwipeAction")
fun setOnSwipeAction(view: SwipeRefreshLayout, viewModel: MainFeedViewModel) {
    view.setOnRefreshListener {
        viewModel.onRefresh()
        view.isRefreshing = false
    }
}

