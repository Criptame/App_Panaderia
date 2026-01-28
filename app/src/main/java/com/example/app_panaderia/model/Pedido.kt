package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val compradorId: Long,
    val repartidorId: Long? = null,
    val total: Double,
    val estado: String,
    val fecha: String,
    val direccionEntrega: String
)
