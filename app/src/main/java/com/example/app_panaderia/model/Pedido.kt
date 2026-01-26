package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "comprador_id")  // ¡Esta es la columna real!
    val compradorId: Long,

    @ColumnInfo(name = "repartidor_id")  // ¡Esta es la columna real!
    val repartidorId: Long? = null,

    val total: Double = 0.0,
    val estado: String = "Pendiente",
    val fecha: String = "",

    @ColumnInfo(name = "direccion_entrega")
    val direccionEntrega: String = "",

    @ColumnInfo(name = "metodo_pago")
    val metodoPago: String = "Efectivo",

    @ColumnInfo(name = "notas")
    val notas: String = ""
)