package com.example.app_panaderia.model.data

import androidx.room.*
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow

// Añadir anotación @Dao
@Dao
interface ProductoDao {

    // Obtener todos los productos como Flow para observación reactiva
    @Query("SELECT * FROM productos ORDER BY fechaCreacion DESC")
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

    // Buscar productos por nombre
    @Query("SELECT * FROM productos WHERE nombre LIKE :query OR descripcion LIKE :query")
    fun searchProductos(query: String): Flow<List<Pan>>
}