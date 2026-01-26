package com.example.app_panaderia.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.model.DetallePedido

// Añadir anotación @Database
@Database(
<<<<<<< HEAD
    entities = [
        Pan::class,
        Comprador::class,
        Pedido::class,
        DetallePedido::class
    ],
    version = 7,  // Incrementar versión por cambios de esquema
=======
    entities = [Pan::class],
    version = 1,
>>>>>>> parent of 340207c (ADDE)
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
<<<<<<< HEAD
    abstract fun compradorDao(): CompradorDao
    abstract fun pedidoDao(): PedidoDao
=======
>>>>>>> parent of 340207c (ADDE)

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
<<<<<<< HEAD
                    .fallbackToDestructiveMigration()  // Esto eliminará datos antiguos
=======
                    .fallbackToDestructiveMigration() // Elimina y recrea en migraciones
>>>>>>> parent of 340207c (ADDE)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}