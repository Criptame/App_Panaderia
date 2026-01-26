package com.example.app_panaderia.model.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompradorRepository @Inject constructor(
    private val compradorDao: CompradorDao
) {

    fun obtenerTodos(): Flow<List<Comprador>> {
        return compradorDao.getAll()
    }

    // Método para obtener como Flow
    fun obtenerPorIdFlow(id: Long): Flow<Comprador?> {
        return compradorDao.getByIdFlow(id)
    }

    // Método suspend para obtener directamente
    suspend fun obtenerPorId(id: Long): Comprador? {
        return compradorDao.getById(id)
    }

    suspend fun registrarComprador(comprador: Comprador): Long {
        // Verificar si el email ya existe
        val existente = compradorDao.findByEmail(comprador.email)
        if (existente != null) {
            throw Exception("El email ya está registrado")
        }
        return compradorDao.insert(comprador)
    }

    suspend fun actualizarComprador(comprador: Comprador) {
        compradorDao.update(comprador)
    }

    suspend fun eliminarComprador(comprador: Comprador) {
        compradorDao.delete(comprador)
    }

    suspend fun iniciarSesion(email: String, password: String): Comprador? {
        return compradorDao.login(email, password)
    }

    suspend fun buscarPorEmail(email: String): Comprador? {
        return compradorDao.findByEmail(email)
    }

    // Método alternativo usando Flow
    fun buscarPorEmailFlow(email: String): Flow<Comprador?> {
        return flow {
            val comprador = compradorDao.findByEmail(email)
            emit(comprador)
        }
    }
}