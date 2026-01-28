package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_panaderia.model.Pan
import com.example.app_panaderia.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UsuarioUiState())
    val uiState: StateFlow<UsuarioUiState> = _uiState.asStateFlow()

    private val _catalogo = MutableStateFlow<List<Pan>>(emptyList())
    val catalogo: StateFlow<List<Pan>> = _catalogo.asStateFlow()

    init {
        loadUserData()
        loadCatalogo()
    }

    private fun loadUserData() {
        viewModelScope.launch {
            _uiState.value = UsuarioUiState(
                id = "user123",
                nombre = "Juan Perez",
                email = "juan.perez@example.com",
                esAdmin = false
            )
        }
    }

    private fun loadCatalogo() {
        viewModelScope.launch {
            // Precios en pesos chilenos (CLP)
            val samplePanes = listOf(
                Pan(nombre = "Marraqueta", descripcion = "Crujiente y delicioso, ideal para sándwiches.", precio = 500.0, cantidad = 0, categoria = "Panadería"),
                Pan(nombre = "Hallulla", descripcion = "Pan plano y redondo, perfecto para el desayuno.", precio = 450.0, cantidad = 0, categoria = "Panadería"),
                Pan(nombre = "Pan Amasado", descripcion = "Un pan casero, suave y delicioso.", precio = 600.0, cantidad = 0, categoria = "Panadería"),
                Pan(nombre = "Dobladita", descripcion = "Masa doblada y horneada, crujiente por fuera.", precio = 550.0, cantidad = 0, categoria = "Panadería")
            )
            _catalogo.value = samplePanes
        }
    }

    fun cerrarSesion() {
        viewModelScope.launch {
            println("Cerrando sesión...")
        }
    }
}
