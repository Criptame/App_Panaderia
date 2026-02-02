package com.example.app_panaderia.data.remote.api

import com.example.app_panaderia.data.remote.dto.PanDto
import retrofit2.Response
import retrofit2.http.*

interface PanApiService {

    // Obtener todos los productos (igual que tu API)
    @GET("api/productos")
    suspend fun getAllProductos(): Response<List<PanDto>>

    // Obtener producto por ID
    @GET("api/productos/{id}")
    suspend fun getProductoById(@Path("id") id: Long): Response<PanDto>

    // Crear nuevo producto
    @POST("api/productos")
    suspend fun createProducto(@Body producto: PanDto): Response<PanDto>

    // Actualizar producto
    @PUT("api/productos/{id}")
    suspend fun updateProducto(
        @Path("id") id: Long,
        @Body producto: PanDto
    ): Response<PanDto>

    // Eliminar producto
    @DELETE("api/productos/{id}")
    suspend fun deleteProducto(@Path("id") id: Long): Response<Void>
}