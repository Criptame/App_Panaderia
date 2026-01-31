// AuthViewModel.kt en package com.example.app_panaderia.viewModels
package com.example.app_panaderia.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated = _isAuthenticated.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    // Credenciales hardcodeadas (en producción usarías base de datos)
    private val validCredentials = mapOf(
        "admin" to "admin123",
        "panaderia" to "pan123"
    )

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            // Simular llamada a API
            delay(1000)

            if (username.isBlank() || password.isBlank()) {
                _errorMessage.value = "Usuario y contraseña son requeridos"
                _isAuthenticated.value = false
            } else if (validCredentials[username] == password) {
                _isAuthenticated.value = true
                _errorMessage.value = null
            } else {
                _errorMessage.value = "Credenciales incorrectas"
                _isAuthenticated.value = false
            }

            _isLoading.value = false
        }
    }

    fun logout() {
        _isAuthenticated.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }
}