// CarritoViewModel.kt en package com.example.app_panaderia.viewModels
package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_panaderia.model.CarritoItem
import com.example.app_panaderia.model.Pan
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CarritoViewModel : ViewModel() {
    private val _carritoItems = MutableStateFlow<List<CarritoItem>>(emptyList())
    val carritoItems: StateFlow<List<CarritoItem>> = _carritoItems.asStateFlow()

    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()

    init {
        calcularTotal()
    }

    fun agregarAlCarrito(pan: Pan) {
        viewModelScope.launch {
            val items = _carritoItems.value.toMutableList()
            val existingItem = items.find { it.productoId == pan.id }

            if (existingItem != null) {
                // Si ya existe, aumentar cantidad
                val index = items.indexOf(existingItem)
                items[index] = existingItem.copy(cantidad = existingItem.cantidad + 1)
            } else {
                // Si no existe, agregar nuevo item
                items.add(
                    CarritoItem(
                        productoId = pan.id,
                        nombre = pan.nombre,
                        precio = pan.precio,
                        cantidad = 1,
                        imagenUrl = pan.imagenUrl
                    )
                )
            }

            _carritoItems.value = items
            calcularTotal()
        }
    }

    fun actualizarCantidad(productoId: Long, nuevaCantidad: Int) {
        viewModelScope.launch {
            if (nuevaCantidad <= 0) {
                eliminarDelCarrito(productoId)
                return@launch
            }

            val items = _carritoItems.value.toMutableList()
            val index = items.indexOfFirst { it.productoId == productoId }

            if (index != -1) {
                items[index] = items[index].copy(cantidad = nuevaCantidad)
                _carritoItems.value = items
                calcularTotal()
            }
        }
    }

    fun eliminarDelCarrito(productoId: Long) {
        viewModelScope.launch {
            val items = _carritoItems.value.toMutableList()
            items.removeAll { it.productoId == productoId }
            _carritoItems.value = items
            calcularTotal()
        }
    }

    fun limpiarCarrito() {
        viewModelScope.launch {
            _carritoItems.value = emptyList()
            calcularTotal()
        }
    }

    private fun calcularTotal() {
        viewModelScope.launch {
            _total.value = _carritoItems.value.sumOf { it.subtotal }
        }
    }

    fun getCantidadEnCarrito(productoId: Long): Int {
        return _carritoItems.value.find { it.productoId == productoId }?.cantidad ?: 0
    }
}