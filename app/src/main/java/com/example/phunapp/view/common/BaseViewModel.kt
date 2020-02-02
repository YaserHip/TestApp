package com.example.phunapp.view.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val isLoading = MutableLiveData<Boolean>().apply { value = false }
}