// En model/ui/
package com.example.app_panaderia.model.ui

import com.example.app_panaderia.model.*

data class UsuarioUiState(
    val id: Long = 0,
    val nombre: String = "",
    val email: String = "",
    val rol: String = "",
    val direccion: String? = null,
    val telefono: String? = null
)

data class CarritoState(
    val items: List<CarritoItem> = emptyList(),
    val total: Double = 0.0,
    val direccionEntrega: String = ""
)

data class PedidoUiState(
    val id: Long = 0,
    val total: Double = 0.0,
    val estado: String = "",
    val fecha: String = "",
    val direccion: String = "",
    val items: List<DetallePedido> = emptyList()
)