package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {
    private val _productos = MutableStateFlow<List<Pan>>(emptyList())
    val productos: StateFlow<List<Pan>> = _productos.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        cargarProductos()
    }

    private fun cargarProductos() {
        viewModelScope.launch {
            _isLoading.value = true
            delay(500) // Simular carga

            _productos.value = listOf(
                Pan(
                    id = 1,
                    nombre = "Pan Amasado Tradicional",
                    descripcion = "Pan artesanal horneado a leña",
                    precio = 1500.0,
                    cantidad = 25,
                    categoria = "Tradicional",
                    disponible = true
                ),
                Pan(
                    id = 2,
                    nombre = "Marraqueta Clásica",
                    descripcion = "Crujiente por fuera, suave por dentro",
                    precio = 1200.0,
                    cantidad = 40,
                    categoria = "Tradicional",
                    disponible = true
                ),
                Pan(
                    id = 3,
                    nombre = "Pan de Molde Integral",
                    descripcion = "Ideal para sandwiches saludables",
                    precio = 2500.0,
                    cantidad = 15,
                    categoria = "Integral",
                    disponible = true
                ),
                Pan(
                    id = 4,
                    nombre = "Pan Brioche",
                    descripcion = "Dulce y esponjoso, perfecto para desayuno",
                    precio = 1800.0,
                    cantidad = 10,
                    categoria = "Dulce",
                    disponible = true
                ),
                Pan(
                    id = 5,
                    nombre = "Pan Ciabatta",
                    descripcion = "Italiano con miga alveolada",
                    precio = 2200.0,
                    cantidad = 8,
                    categoria = "Internacional",
                    disponible = false
                )
            )

            _isLoading.value = false
        }
    }

    fun agregarProducto(pan: Pan) {
        viewModelScope.launch {
            val newId = (_productos.value.maxOfOrNull { it.id } ?: 0) + 1
            val nuevoPan = pan.copy(id = newId)
            _productos.value = _productos.value + nuevoPan
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch {
            _productos.value = _productos.value.filter { it.id != id }
        }
    }

    fun actualizarProducto(pan: Pan) {
        viewModelScope.launch {
            _productos.value = _productos.value.map {
                if (it.id == pan.id) pan else it
            }
        }
    }

    fun getProductoById(id: Long): Pan? {
        return _productos.value.find { it.id == id }
    }
}