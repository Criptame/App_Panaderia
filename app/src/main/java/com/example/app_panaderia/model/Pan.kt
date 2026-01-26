package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "productos")
data class Pan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,

    @ColumnInfo(name = "imagen_url")
    val imagenUrl: String? = null,

    @ColumnInfo(name = "fecha_creacion")  // ¡Esta es la columna en SQL!
    val fechaCreacion: Date = Date(),  // ¡Esta es la propiedad en Kotlin!

    val categoria: String,
    val disponible: Boolean = true
)