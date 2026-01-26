package com.example.app_panaderia.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "compradores")
data class Comprador(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val nombre: String,

    @ColumnInfo(name = "email", index = true)  // Agregar índice para búsquedas rápidas
    val email: String,

    val telefono: String,
    val direccion: String,

    @ColumnInfo(name = "password")
    val password: String = "",

    @ColumnInfo(name = "fecha_registro")
    val fechaRegistro: Date = Date(),

    @ColumnInfo(name = "activo")
    val activo: Boolean = true
)