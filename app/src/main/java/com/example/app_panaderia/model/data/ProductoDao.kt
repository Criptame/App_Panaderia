package com.example.app_panaderia.model.data

import androidx.room.*
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    // Obtener todos los productos como Flow para observación reactiva
    @Query("SELECT * FROM productos ORDER BY fecha_creacion DESC")
    fun getAllProductos(): Flow<List<Pan>>

    // Obtener un producto por su ID
    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getProductoById(id: Long): Pan?

    // Obtener productos por categoría
    @Query("SELECT * FROM productos WHERE categoria = :categoria ORDER BY nombre")
    fun getProductosByCategoria(categoria: String): Flow<List<Pan>>

    // Insertar un producto (si existe, reemplazar)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Pan): Long

    // Insertar múltiples productos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<Pan>)

    // Actualizar un producto
    @Update
    suspend fun updateProducto(producto: Pan): Int

    // Eliminar un producto
    @Delete
    suspend fun deleteProducto(producto: Pan): Int

    // Eliminar todos los productos
    @Query("DELETE FROM productos")
    suspend fun deleteAllProductos()

    // Buscar productos por nombre o descripción
    @Query("SELECT * FROM productos WHERE nombre LIKE :query OR descripcion LIKE :query")
    fun searchProductos(query: String): Flow<List<Pan>>

    // Obtener productos disponibles
    @Query("SELECT * FROM productos WHERE disponible = 1 ORDER BY nombre")
    fun getProductosDisponibles(): Flow<List<Pan>>

    // Obtener productos por stock bajo
    @Query("SELECT * FROM productos WHERE cantidad < :stockMinimo ORDER BY cantidad ASC")
    fun getProductosStockBajo(stockMinimo: Int = 10): Flow<List<Pan>>

    // Actualizar stock de un producto
    @Query("UPDATE productos SET cantidad = :nuevaCantidad WHERE id = :id")
    suspend fun actualizarStock(id: Long, nuevaCantidad: Int)

    // Actualizar disponibilidad
    @Query("UPDATE productos SET disponible = :disponible WHERE id = :id")
    suspend fun actualizarDisponibilidad(id: Long, disponible: Boolean)

    // Obtener categorías únicas
    @Query("SELECT DISTINCT categoria FROM productos ORDER BY categoria")
    fun getCategorias(): Flow<List<String>>

    // Contar productos por categoría - CORREGIDO
    @Query("SELECT categoria, COUNT(*) as total FROM productos GROUP BY categoria")
    suspend fun contarProductosPorCategoria(): List<CategoriaConteo>
}