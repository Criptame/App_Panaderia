package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.util.Date

@Entity(tableName = "pedidos")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "usuario_id")
    val usuarioId: Long,

    @ColumnInfo(name = "repartidor_id")
    val repartidorId: Long? = null,

    val total: Double,

    val estado: String, // "pendiente", "preparando", "en_camino", "entregado", "cancelado"

    @ColumnInfo(name = "fecha_pedido")
    val fechaPedido: Date = Date(),

    @ColumnInfo(name = "fecha_entrega")
    val fechaEntrega: Date? = null,

    val direccion: String,

    val notas: String? = null,

    @ColumnInfo(name = "metodo_pago")
    val metodoPago: String = "efectivo" // "efectivo", "tarjeta"
)