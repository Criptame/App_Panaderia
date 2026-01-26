package com.example.app_panaderia.model.data

import com.example.app_panaderia.model.DetallePedido
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.model.Pedido
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PedidoRepository @Inject constructor(
    private val pedidoDao: PedidoDao,
    private val productoDao: ProductoDao
) {

    fun obtenerPedidosPorComprador(compradorId: Long): Flow<List<PedidoDao.PedidoConProductos>> {
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
        productos: List<Pan>,
        cantidades: List<Int>
    ): Long {
        // Insertar el pedido principal
        val pedidoId = pedidoDao.insertPedido(pedido)

        // Insertar los detalles del pedido
        productos.forEachIndexed { index, producto ->
            val detalle = DetallePedido(
                pedidoId = pedidoId,
                productoId = producto.id,
                cantidad = cantidades[index],
                precioUnitario = producto.precio
            )
            pedidoDao.insertDetalle(detalle)

            // Actualizar el stock del producto
            val nuevoStock = producto.cantidad - cantidades[index]
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
        val pedidoConDetalles = obtenerPedidoConDetalles(pedidoId)
        pedidoConDetalles?.detalles?.forEach { detalle ->
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

    suspend fun obtenerPedidoConDetalles(pedidoId: Long): PedidoDao.PedidoConProductos? {
        val pedidos = pedidoDao.getPedidosPorComprador(0).firstOrNull()
        return pedidos?.find { it.pedido.id == pedidoId }
    }
}