package com.example.phunapp.view.adapters.mainfeed

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.phunapp.data.models.ItemModel
import com.example.phunapp.databinding.ItemFeedBinding
import com.example.phunapp.view.ui.mainfeed.MainFeedViewModel

class FeedViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemFeedBinding.bind(view)

    fun bind(itemModel: ItemModel, viewModel: MainFeedViewModel){
        binding.item = itemModel
        binding.viewmodel = viewModel
    }
}