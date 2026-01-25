package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

// Añadir anotación @Entity y definir nombre de tabla
@Entity(tableName = "productos")
data class Pan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val cantidad: Int,
    val imagenUrl: String? = null,
    val fechaCreacion: Date = Date(),
    val categoria: String,
    val disponible: Boolean = true
)