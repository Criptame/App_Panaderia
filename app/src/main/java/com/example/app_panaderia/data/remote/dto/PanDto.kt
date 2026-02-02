package com.example.app_panaderia.data.remote.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PanDto(
    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String? = "",

    @SerializedName("precio")
    val precio: Double,

    @SerializedName("cantidad")
    val cantidad: Int,

    @SerializedName("imagenUrl")
    val imagenUrl: String? = null,

    @SerializedName("fechaCreacion")
    val fechaCreacion: Date? = Date(),

    @SerializedName("categoria")
    val categoria: String? = "General",

    @SerializedName("disponible")
    val disponible: Boolean? = true
)