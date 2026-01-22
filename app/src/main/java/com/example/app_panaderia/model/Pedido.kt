package com.example.app_panaderia.model

import androidx.compose.runtime.Immutable

@Immutable
data class Pedido(
    val id: String = "",
    val compradorId: String = "", // ID del usuario que hizo el pedido
    val repartidorId: String? = null, // ID del repartidor asignado
    val panes: List<Pan> = emptyList(),
    val total: Double = 0.0,
    val estado: String = "Pendiente", // Ej: Pendiente, En reparto, Entregado
    val fecha: String = ""
)
