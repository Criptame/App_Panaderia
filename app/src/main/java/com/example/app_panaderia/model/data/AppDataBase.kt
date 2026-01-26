package com.example.app_panaderia.model.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.model.DetallePedido

@Database(
    entities = [
        Pan::class,
        Comprador::class,
        Pedido::class,
        DetallePedido::class
    ],
    version = 7,  // Incrementar versión por cambios de esquema
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
    abstract fun compradorDao(): CompradorDao
    abstract fun pedidoDao(): PedidoDao

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
                    .fallbackToDestructiveMigration()  // Esto eliminará datos antiguos
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
