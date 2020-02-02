package com.example.phunapp.data.remote

import com.example.phunapp.data.models.ItemModel
import retrofit2.http.GET

interface RemoteService {
    @GET("/phunware-services/dev-interview-homework/master/feed.json")
    suspend fun getListAsync(): List<ItemModel>
}