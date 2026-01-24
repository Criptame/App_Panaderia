package com.example.app_panaderia.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_panaderia.model.*

@Database(
    entities = [Pan::class],
    version = 1,
    exportSchema = false)

abstract class AppDataBase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    companion object {
        @Volatile private var INSTANCE: AppDataBase? = null

        fun get(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "productos.db"
                ).build().also { INSTANCE = it }
            }
    }
}