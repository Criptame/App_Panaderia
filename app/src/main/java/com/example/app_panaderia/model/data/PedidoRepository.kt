package com.example.app_panaderia.model.data

import com.example.app_panaderia.model.DetallePedido
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.model.Pedido
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class PedidoRepository @Inject constructor(
    private val pedidoDao: PedidoDao,
    private val productoDao: ProductoDao
) {

    // Cambiar el tipo de retorno
    fun obtenerPedidosPorComprador(compradorId: Long): Flow<List<Pedido>> {
        return pedidoDao.getPedidosPorComprador(compradorId)
    }

    fun obtenerPedidosPorEstado(estado: String): Flow<List<Pedido>> {
        return pedidoDao.getPedidosPorEstado(estado)
    }

    suspend fun obtenerPedidoPorId(pedidoId: Long): Pedido? {
        return pedidoDao.getPedidoById(pedidoId)
    }

    suspend fun crearPedido(
        pedido: Pedido,
        productos: Map<Pan, Int>  // Cambiar a Map para evitar ambigüedad
    ): Long {
        // Insertar el pedido principal
        val pedidoId = pedidoDao.insertPedido(pedido)

        // Insertar los detalles del pedido
        productos.forEach { (producto, cantidad) ->
            val detalle = DetallePedido(
                pedidoId = pedidoId,
                productoId = producto.id,
                cantidad = cantidad,
                precioUnitario = producto.precio
            )
            pedidoDao.insertDetalle(detalle)

            // Actualizar el stock del producto
            val nuevoStock = producto.cantidad - cantidad
            productoDao.updateProducto(
                producto.copy(cantidad = nuevoStock)
            )
        }

        return pedidoId
    }

    suspend fun actualizarEstadoPedido(pedidoId: Long, nuevoEstado: String) {
        val pedido = pedidoDao.getPedidoById(pedidoId)
        pedido?.let {
            pedidoDao.updatePedido(it.copy(estado = nuevoEstado))
        }
    }

    suspend fun cancelarPedido(pedidoId: Long) {
        // Primero, restaurar el stock de los productos
        val detalles = pedidoDao.getDetallesPorPedido(pedidoId)
        detalles.forEach { detalle ->
            val producto = productoDao.getProductoById(detalle.productoId)
            producto?.let {
                productoDao.updateProducto(
                    it.copy(cantidad = it.cantidad + detalle.cantidad)
                )
            }
        }

        // Actualizar estado a "Cancelado"
        actualizarEstadoPedido(pedidoId, "Cancelado")
    }

    suspend fun obtenerPedidoConDetalles(pedidoId: Long): PedidoConDetalles? {
        val pedido = pedidoDao.getPedidoById(pedidoId)
        return pedido?.let {
            val detalles = pedidoDao.getDetallesPorPedido(pedidoId)
            PedidoConDetalles(pedido, detalles)
        }
    }

    // Data class para pedido con detalles
    data class PedidoConDetalles(
        val pedido: Pedido,
        val detalles: List<DetallePedido>
    )
}