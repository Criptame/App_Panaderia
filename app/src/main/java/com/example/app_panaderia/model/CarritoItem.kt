// CarritoItem.kt en package com.example.app_panaderia.model
package com.example.app_panaderia.model

data class CarritoItem(
    val productoId: Long,
    val nombre: String,
    val precio: Double,          // Precio unitario
    val cantidad: Int = 1,
    val imagenUrl: String? = null
) {
    val subtotal: Double         // Precio × cantidad
        get() = precio * cantidad
}