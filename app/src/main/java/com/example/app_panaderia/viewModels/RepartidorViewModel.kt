package com.example.app_panaderia.viewModels

import androidx.lifecycle.*
import com.example.app_panaderia.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

class RepartidorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState.asStateFlow()

    private val _pedidos = MutableStateFlow<List<Pedido>>(emptyList())
    val pedidos: StateFlow<List<Pedido>> = _pedidos.asStateFlow()

    init {
        loadRepartidorData()
        loadPedidosAsignados()
    }

    private fun loadRepartidorData() {
        viewModelScope.launch {
            // Simulación de carga de datos del repartidor
            _uiState.value = UsuarioUiState(
                id = "456",
                nombre = "Ana Gómez",
                email = "ana.gomez@example.com",
                esAdmin = false
            )
        }
    }

    private fun loadPedidosAsignados() {
        viewModelScope.launch {
            // Simulación de carga de pedidos asignados con IDs de tipo Long
            val samplePedidos = listOf(
                Pedido(id = 2L, usuarioId = 102L, repartidorId = 456L, total = 15.00, estado = "En reparto", fechaPedido = Date(), direccion = "Avenida Siempreviva 742"),
                Pedido(id = 4L, usuarioId = 103L, repartidorId = 456L, total = 12.50, estado = "Pendiente de recogida", fechaPedido = Date(), direccion = "Calle Falsa 123"),
                Pedido(id = 5L, usuarioId = 104L, repartidorId = 456L, total = 8.75, estado = "Pendiente de recogida", fechaPedido = Date(), direccion = "Elm Street 1428")
            )
            _pedidos.value = samplePedidos
        }
    }
}
