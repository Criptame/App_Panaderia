package com.example.app_panaderia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app_panaderia.data.local.dao.PanDao
import com.example.app_panaderia.model.Pan

@Database(entities = [Pan::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PanaderiaDatabase : RoomDatabase() {
    abstract fun panDao(): PanDao
}
