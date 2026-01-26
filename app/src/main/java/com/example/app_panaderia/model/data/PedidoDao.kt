package com.example.app_panaderia.model.data

import androidx.room.*
import com.example.app_panaderia.model.DetallePedido
import com.example.app_panaderia.model.Pedido
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Transaction
    @Query("SELECT * FROM pedidos WHERE comprador_id = :compradorId ORDER BY fecha DESC")
    fun getPedidosPorComprador(compradorId: Long): Flow<List<PedidoConProductos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPedido(pedido: Pedido): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetalle(detalle: DetallePedido)

    @Update
    suspend fun updatePedido(pedido: Pedido)

    @Query("DELETE FROM pedidos WHERE id = :pedidoId")
    suspend fun deletePedido(pedidoId: Long)

    @Query("SELECT * FROM pedidos WHERE estado = :estado")
    fun getPedidosPorEstado(estado: String): Flow<List<Pedido>>

    @Query("SELECT * FROM pedidos WHERE id = :pedidoId")
    suspend fun getPedidoById(pedidoId: Long): Pedido?
}

data class PedidoConProductos(
    @Embedded val pedido: Pedido,
    @Relation(
        parentColumn = "id",
        entityColumn = "pedidoId"
    )
    val detalles: List<DetallePedido>
)
