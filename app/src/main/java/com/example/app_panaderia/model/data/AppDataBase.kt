package com.example.app_panaderia.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_panaderia.model.Pan

// Añadir anotación @Database
@Database(
    entities = [Pan::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "panaderia_database"
                )
                    .fallbackToDestructiveMigration() // Elimina y recrea en migraciones
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}