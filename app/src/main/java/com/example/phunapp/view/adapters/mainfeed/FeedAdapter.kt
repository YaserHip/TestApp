package com.example.phunapp.view.adapters.mainfeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.phunapp.R
import com.example.phunapp.data.models.ItemModel
import com.example.phunapp.view.ui.mainfeed.MainFeedViewModel

class FeedAdapter(private val viewModel: MainFeedViewModel) :
    RecyclerView.Adapter<FeedViewHolder>() {

    private val items: MutableList<ItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder =
        FeedViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        )

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) =
        holder.bind(items[position], viewModel)

    fun update(items : List<ItemModel>){
        val diffCallback = FeedDiffCallback(items, this.items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items.clear()
        this.items.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }
}