package com.example.phunapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey
    val id: String,
    val description: String,
    val title: String,
    val timestamp: String,
    val image: String? = "",
    val date: String,
    val locationline1: String,
    val locationline2: String
)