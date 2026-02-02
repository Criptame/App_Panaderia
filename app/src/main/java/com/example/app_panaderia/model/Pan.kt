package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "productos")
data class Pan(
    @PrimaryKey(autoGenerate = false)  // ID viene de API
    val id: Long = 0,

    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,

    @ColumnInfo(name = "imagen_url")
    val imagenUrl: String? = null,

    @ColumnInfo(name = "fecha_creacion")
    val fechaCreacion: Date = Date(),

    val categoria: String,
    val disponible: Boolean = true,

    @ColumnInfo(name = "last_updated")  // Para saber si está actualizado
    val lastUpdated: Long = System.currentTimeMillis()
)