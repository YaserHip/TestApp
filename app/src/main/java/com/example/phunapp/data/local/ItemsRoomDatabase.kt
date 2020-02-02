package com.example.phunapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phunapp.data.local.dao.ItemDao
import com.example.phunapp.data.models.ItemModel

@Database(entities = [ItemModel::class], version = 1)
public abstract class ItemsRoomDatabase : RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        private var instance: ItemsRoomDatabase? = null

        fun getDatabase(context: Context): ItemsRoomDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsRoomDatabase::class.java,
                    "items_database"
                ).build()
                this.instance = instance
                return instance
            }
        }
    }
}