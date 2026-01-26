package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo

@Entity(tableName = "pedidos")
@Immutable
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "comprador_id")
    val compradorId: Long,

    @ColumnInfo(name = "repartidor_id")
    val repartidorId: Long? = null,

    val total: Double = 0.0,
    val estado: String = "Pendiente", // Pendiente, Preparando, Enviado, Entregado, Cancelado
    val fecha: String = "",

    @ColumnInfo(name = "direccion_entrega")
    val direccionEntrega: String = "",

    @ColumnInfo(name = "metodo_pago")
    val metodoPago: String = "Efectivo", // Efectivo, Tarjeta, Transferencia

    @ColumnInfo(name = "notas")
    val notas: String = ""
)