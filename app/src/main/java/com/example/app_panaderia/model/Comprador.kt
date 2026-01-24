package com.example.app_panaderia.model

import androidx.compose.runtime.Immutable

@Immutable
data class Comprador(
    val id: String = "",
    val nombre: String = "",
    val email: String = "",
    val direccion: String = "",
    val telefono: String = ""
)