package com.example.app_panaderia.model

import androidx.compose.runtime.Immutable

@Immutable
data class Pan(
    val id: String = "",
    val nombre: String = "",
    val descripcion: String = "",
    val precio: Double = 0.0,
    val imagenUrl: String = ""
)
