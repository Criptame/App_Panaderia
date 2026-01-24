package com.example.app_panaderia.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_panaderia.model.*
import com.example.app_panaderia.model.data.ProductoDao

@Database(
    entities = [Pan::class],
    version = 1,
    exportSchema = false)

abstract class AppDataBase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao

}