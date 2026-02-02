package com.example.app_panaderia.data.remote

import com.example.app_panaderia.data.remote.api.PanApiService
import com.example.app_panaderia.data.remote.dto.PanDto
import com.example.app_panaderia.model.Pan
import java.util.Date
import javax.inject.Inject

class PanRemoteDataSource @Inject constructor(
    private val apiService: PanApiService
) {
    // Convertir DTO a Modelo
    private fun PanDto.toPan(): Pan {
        return Pan(
            id = this.id ?: 0,
            nombre = this.nombre,
            descripcion = this.descripcion ?: "",
            precio = this.precio,
            cantidad = this.cantidad,
            imagenUrl = this.imagenUrl,
            fechaCreacion = this.fechaCreacion ?: Date(),
            categoria = this.categoria ?: "General",
            disponible = this.disponible ?: true
        )
    }

    // Convertir Modelo a DTO
    private fun Pan.toDto(): PanDto {
        return PanDto(
            id = if (this.id == 0L) null else this.id,
            nombre = this.nombre,
            descripcion = this.descripcion,
            precio = this.precio,
            cantidad = this.cantidad,
            imagenUrl = this.imagenUrl,
            fechaCreacion = this.fechaCreacion,
            categoria = this.categoria,
            disponible = this.disponible
        )
    }

    suspend fun getAllProductos(): List<Pan> {
        val response = apiService.getAllProductos()
        if (response.isSuccessful) {
            return response.body()?.map { it.toPan() } ?: emptyList()
        }
        throw Exception("Error: ${response.code()} - ${response.message()}")
    }

    suspend fun createProducto(pan: Pan): Pan {
        val response = apiService.createProducto(pan.toDto())
        if (response.isSuccessful) {
            return response.body()!!.toPan()
        }
        throw Exception("Error al crear: ${response.errorBody()?.string()}")
    }

    suspend fun updateProducto(pan: Pan): Pan {
        val response = apiService.updateProducto(pan.id, pan.toDto())
        if (response.isSuccessful) {
            return response.body()!!.toPan()
        }
        throw Exception("Error al actualizar: ${response.errorBody()?.string()}")
    }

    suspend fun deleteProducto(id: Long): Boolean {
        val response = apiService.deleteProducto(id)
        return response.isSuccessful
    }
}