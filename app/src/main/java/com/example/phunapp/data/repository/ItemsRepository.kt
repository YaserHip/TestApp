package com.example.phunapp.data.repository

import androidx.lifecycle.LiveData
import com.example.phunapp.data.local.dao.ItemDao
import com.example.phunapp.data.models.ItemModel
import com.example.phunapp.data.remote.RemoteService
import com.example.phunapp.utils.NetworkBoundResource
import com.example.phunapp.utils.Resource

class ItemsRepository(private val remoteService: RemoteService, private val dao: ItemDao) {

    suspend fun getItemsFromLocalOrRemote(hasToRefresh: Boolean): LiveData<Resource<List<ItemModel>>> {
        return object : NetworkBoundResource<List<ItemModel>, List<ItemModel>>() {
            override fun processResponse(response: List<ItemModel>): List<ItemModel> = response

            override suspend fun saveCallResults(items: List<ItemModel>) = dao.insertItems(items)

            override fun shouldFetch(data: List<ItemModel>?): Boolean =
                data == null || hasToRefresh || data.isEmpty()

            override suspend fun loadFromDb(): List<ItemModel> = dao.getAllItems()

            override suspend fun createCallAsync(): List<ItemModel> = remoteService.getListAsync()

        }.build().asliveData()
    }

}