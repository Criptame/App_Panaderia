package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "nombre_completo")
    val nombre: String,

    val email: String,

    @ColumnInfo(name = "contrasena_hash")
    val contrasenaHash: String,

    val rol: String, // "admin", "usuario", "repartidor"

    val telefono: String? = null,

    val direccion: String? = null,

    @ColumnInfo(name = "fecha_registro")
    val fechaRegistro: Date = Date(),

    val activo: Boolean = true
)