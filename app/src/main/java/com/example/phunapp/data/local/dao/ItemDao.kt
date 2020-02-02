package com.example.phunapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phunapp.data.models.ItemModel

@Dao
abstract class ItemDao {
    @Query("SELECT * FROM ItemModel")
    abstract suspend fun getAllItems(): List<ItemModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertItems(itemModel: List<ItemModel>)
}
