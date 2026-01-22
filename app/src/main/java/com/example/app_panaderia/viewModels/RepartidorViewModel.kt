package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_panaderia.model.Pedido
import com.example.app_panaderia.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
                id = "repartidor456",
                nombre = "Ana Gómez",
                email = "ana.gomez@example.com",
                esAdmin = false
            )
        }
    }

    private fun loadPedidosAsignados() {
        viewModelScope.launch {
            // Simulación de carga de pedidos asignados
            val samplePedidos = listOf(
                Pedido(id = "PED-002", compradorId = "USR-002", repartidorId = "repartidor456", total = 15.00, estado = "En reparto", fecha = "2024-05-20"),
                Pedido(id = "PED-004", compradorId = "USR-003", repartidorId = "repartidor456", total = 12.50, estado = "Pendiente de recogida", fecha = "2024-05-21"),
                Pedido(id = "PED-005", compradorId = "USR-004", repartidorId = "repartidor456", total = 8.75, estado = "Pendiente de recogida", fecha = "2024-05-21")
            )
            _pedidos.value = samplePedidos
        }
    }
}
