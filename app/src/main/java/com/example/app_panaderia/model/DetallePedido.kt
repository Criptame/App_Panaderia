package com.example.app_panaderia.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "detalle_pedidos",
    foreignKeys = [
        ForeignKey(
            entity = Pedido::class,
            parentColumns = ["id"],
            childColumns = ["pedido_id"],  // Usar nombre de columna, no propiedad
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Pan::class,
            parentColumns = ["id"],
            childColumns = ["producto_id"],  // Usar nombre de columna, no propiedad
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["pedido_id"]),  // Usar nombre de columna
        Index(value = ["producto_id"]), // Usar nombre de columna
        Index(value = ["pedido_id", "producto_id"], unique = true)
    ]
)
data class DetallePedido(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "pedido_id")
    val pedidoId: Long,  // Propiedad: pedidoId, Columna: pedido_id

    @ColumnInfo(name = "producto_id")
    val productoId: Long,  // Propiedad: productoId, Columna: producto_id

    val cantidad: Int,

    @ColumnInfo(name = "precio_unitario")
    val precioUnitario: Double
) {
    // Propiedad calculada (no se almacena en la BD)
    val subtotal: Double
        get() = cantidad * precioUnitario
}