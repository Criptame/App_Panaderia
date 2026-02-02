package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_panaderia.data.repository.PanRepository
import com.example.app_panaderia.model.Pan
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoViewModel @Inject constructor(
    private val repository: PanRepository
) : ViewModel() {

    // StateFlow que observa los productos
    val productos: StateFlow<List<Pan>> = repository.getAllProductos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Estado de conexión (opcional)
    val hasConnection: Boolean
        get() = true // Implementar lógica de detección de internet

    fun agregarProducto(pan: Pan) {
        viewModelScope.launch {
            val success = repository.createProducto(pan)
            // Puedes mostrar mensaje según 'success'
            // success = true → creado en API
            // success = false → guardado localmente (offline)
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch {
            val success = repository.deleteProducto(id)
            // Manejar éxito/fallo
        }
    }

    // Sincronizar cuando recuperes conexión
    fun sincronizarDatos() {
        viewModelScope.launch {
            repository.syncPendingOperations()
        }
    }
}