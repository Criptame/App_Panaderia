package com.example.app_panaderia.model.data

import androidx.room.*
import com.example.app_panaderia.model.DetallePedido
import com.example.app_panaderia.model.Pedido
import kotlinx.coroutines.flow.Flow

@Dao
interface PedidoDao {

    @Query("SELECT * FROM pedidos WHERE comprador_id = :compradorId ORDER BY fecha DESC")
    fun getPedidosPorComprador(compradorId: Long): Flow<List<Pedido>>

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

    @Query("SELECT * FROM detalle_pedidos WHERE pedido_id = :pedidoId")  // Cambiado a pedido_id
    suspend fun getDetallesPorPedido(pedidoId: Long): List<DetallePedido>

    @Query("SELECT * FROM pedidos WHERE repartidor_id = :repartidorId AND estado IN ('En reparto', 'Pendiente')")
    fun getPedidosPorRepartidor(repartidorId: Long): Flow<List<Pedido>>

    @Query("SELECT * FROM pedidos WHERE estado = 'Pendiente'")
    fun getPedidosPendientes(): Flow<List<Pedido>>

    @Query("SELECT COUNT(*) FROM pedidos WHERE comprador_id = :compradorId AND estado = 'Entregado'")
    suspend fun countPedidosEntregados(compradorId: Long): Int

    @Transaction
    @Query("SELECT * FROM pedidos WHERE id = :pedidoId")
    suspend fun getPedidoConDetalles(pedidoId: Long): PedidoConDetalles?

    data class PedidoConDetalles(
        @Embedded val pedido: Pedido,
        @Relation(
            parentColumn = "id",
            entityColumn = "pedido_id"
        )
        val detalles: List<DetallePedido>
    )
}