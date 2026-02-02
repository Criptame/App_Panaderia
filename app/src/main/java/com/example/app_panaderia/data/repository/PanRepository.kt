package com.example.app_panaderia.data.repository

import com.example.app_panaderia.data.local.dao.PanDao
import com.example.app_panaderia.data.remote.PanRemoteDataSource
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PanRepository @Inject constructor(
    private val localDataSource: PanDao,
    private val remoteDataSource: PanRemoteDataSource
) {

    // Obtiene productos: Primero de cache, luego actualiza desde API
    fun getAllProductos(): Flow<List<Pan>> = flow {
        try {
            // 1. Primero emitir lo que hay en cache (inmediato)
            localDataSource.getAll().collect { cachedProductos ->
                emit(cachedProductos)

                // 2. Luego intentar actualizar desde API (en segundo plano)
                try {
                    val remoteProductos = remoteDataSource.getAllProductos()
                    if (remoteProductos.isNotEmpty()) {
                        // Guardar en cache y emitir actualizado
                        localDataSource.deleteAll()
                        localDataSource.insertAll(remoteProductos)
                        emit(remoteProductos)
                    }
                } catch (e: Exception) {
                    // Si falla API, mantenemos cache
                    // Puedes loguear el error: Log.d("API", "Error: ${e.message}")
                }
            }
        } catch (e: Exception) {
            // Si no hay nada en cache, intentar API directamente
            try {
                val remoteProductos = remoteDataSource.getAllProductos()
                localDataSource.insertAll(remoteProductos)
                emit(remoteProductos)
            } catch (e: Exception) {
                // Si todo falla, emitir lista vacía
                emit(emptyList())
            }
        }
    }

    // Crear producto: Primero en API, luego en cache
    suspend fun createProducto(pan: Pan): Boolean {
        return try {
            // 1. Intentar crear en API
            val createdPan = remoteDataSource.createProducto(pan)
            // 2. Guardar en cache
            localDataSource.insert(createdPan)
            true
        } catch (e: Exception) {
            // Si falla API, guardar solo en cache (modo offline)
            localDataSource.insert(pan.copy(id = generateLocalId()))
            false // Indica que está pendiente de sincronizar
        }
    }

    // Eliminar producto
    suspend fun deleteProducto(id: Long): Boolean {
        return try {
            // 1. Intentar eliminar de API
            val success = remoteDataSource.deleteProducto(id)
            if (success) {
                // 2. Eliminar de cache
                localDataSource.deleteById(id)
            }
            success
        } catch (e: Exception) {
            // Si falla API, marcar como eliminado en cache
            localDataSource.deleteById(id)
            false // Pendiente de sincronizar
        }
    }

    // Sincronizar datos pendientes (opcional)
    suspend fun syncPendingOperations() {
        // Aquí puedes implementar lógica para sincronizar
        // operaciones pendientes cuando haya conexión
    }

    private fun generateLocalId(): Long {
        // Generar ID negativo para datos locales pendientes
        return -System.currentTimeMillis()
    }
}