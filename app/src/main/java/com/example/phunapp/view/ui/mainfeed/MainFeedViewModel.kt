package com.example.phunapp.view.ui.mainfeed

import android.util.Log
import androidx.lifecycle.*
import com.example.phunapp.PhunApp
import com.example.phunapp.data.local.ItemsRoomDatabase
import com.example.phunapp.data.models.ItemModel
import com.example.phunapp.data.remote.RemoteClient
import com.example.phunapp.data.repository.ItemsRepository
import com.example.phunapp.utils.Resource
import com.example.phunapp.utils.Status
import com.example.phunapp.view.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFeedViewModel : BaseViewModel() {

    private val itemsRepository: ItemsRepository
    private val _items = MediatorLiveData<Resource<List<ItemModel>>>()
    val items: LiveData<Resource<List<ItemModel>>> get() = _items
    private var itemsSource: LiveData<Resource<List<ItemModel>>> = MutableLiveData()

    init {
        val dao = ItemsRoomDatabase.getDatabase(PhunApp.instance).itemDao()
        val service = RemoteClient.instance
        itemsRepository = ItemsRepository(service, dao)
        getData(false)
    }

    fun onRefresh() = getData(false)

    private fun getData(hasToRefresh: Boolean) = viewModelScope.launch(Dispatchers.Main) {
        isLoading.value = true
        _items.removeSource(itemsSource)
        withContext(Dispatchers.IO){
            itemsSource = itemsRepository.getItemsFromLocalOrRemote(hasToRefresh)
        }
        _items.addSource(itemsSource) {
            Log.e(MainFeedViewModel::javaClass.name, "HERE")
            _items.value = it
            if (it.status == Status.ERROR)
                Log.e(MainFeedViewModel::class.java.name, it.message)
        }
        isLoading.value = false
    }
}
