package com.example.phunapp.view.adapters.mainfeed

import androidx.recyclerview.widget.DiffUtil
import com.example.phunapp.data.models.ItemModel

class FeedDiffCallback(private val old: List<ItemModel>, private val new: List<ItemModel>) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition].id == new[newItemPosition].id &&
                old[oldItemPosition].description == new[newItemPosition].description &&
                old[oldItemPosition].title == new[newItemPosition].title &&
                old[oldItemPosition].timestamp == new[newItemPosition].timestamp &&
                old[oldItemPosition].image == new[newItemPosition].image &&
                old[oldItemPosition].date == new[newItemPosition].date &&
                old[oldItemPosition].locationline1 == new[newItemPosition].locationline1 &&
                old[oldItemPosition].locationline2 == new[newItemPosition].locationline2
    }

}