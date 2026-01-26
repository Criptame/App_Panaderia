package com.example.app_panaderia.model.data

import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PanRepository @Inject constructor(
    private val productoDao: ProductoDao
) {

    fun getAllProductos(): Flow<List<Pan>> = productoDao.getAllProductos()

    fun getProductosByCategoria(categoria: String): Flow<List<Pan>> =
        productoDao.getProductosByCategoria(categoria)

    suspend fun getProductoById(id: Long): Pan? = productoDao.getProductoById(id)

    suspend fun insertProducto(producto: Pan): Long = productoDao.insertProducto(producto)

    suspend fun updateProducto(producto: Pan): Int = productoDao.updateProducto(producto)

    suspend fun deleteProducto(producto: Pan): Int = productoDao.deleteProducto(producto)

    fun searchProductos(query: String): Flow<List<Pan>> = productoDao.searchProductos("%$query%")

    fun getProductosDisponibles(): Flow<List<Pan>> = productoDao.getProductosDisponibles()

    fun getProductosStockBajo(stockMinimo: Int = 10): Flow<List<Pan>> =
        productoDao.getProductosStockBajo(stockMinimo)

    // Métodos adicionales corregidos
    suspend fun actualizarStock(id: Long, nuevaCantidad: Int) {
        productoDao.actualizarStock(id, nuevaCantidad)
    }

    suspend fun actualizarDisponibilidad(id: Long, disponible: Boolean) {
        productoDao.actualizarDisponibilidad(id, disponible)
    }

    fun getCategorias(): Flow<List<String>> = productoDao.getCategorias()

    // Método para contar productos por categoría - Temporalmente comentado o modificado
    suspend fun contarProductosPorCategoria(): List<CategoriaConteo> {
        return productoDao.contarProductosPorCategoria()
    }

    // Método alternativo para obtener como Map si lo necesitas
    suspend fun getConteoProductosPorCategoriaMap(): Map<String, Int> {
        return contarProductosPorCategoria()
            .associate { it.categoria to it.total }
    }

    suspend fun insertSampleData() {
        val sampleProductos = listOf(
            Pan(
                nombre = "Pan Amasado",
                descripcion = "Pan suave por dentro y por fuera",
                precio = 250.00,
                cantidad = 50,
                categoria = "Panadería",
                imagenUrl = null
            ),
            Pan(
                nombre = "Pan Molde",
                descripcion = "Pan moldeado",
                precio = 3500.00,
                cantidad = 30,
                categoria = "Panaderia",
                imagenUrl = null
            ),
            Pan(
                nombre = "Pan De Ajo",
                descripcion = "Pan realizado con mantequilla de ajo",
                precio = 5000.00,
                cantidad = 100,
                categoria = "Panaderia",
                imagenUrl = null
            )
        )
        productoDao.insertAll(sampleProductos)
    }
}