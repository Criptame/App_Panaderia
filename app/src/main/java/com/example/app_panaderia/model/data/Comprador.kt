package com.example.app_panaderia.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "compradores")
data class Comprador(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val email: String,
    val telefono: String,
    val direccion: String
)
