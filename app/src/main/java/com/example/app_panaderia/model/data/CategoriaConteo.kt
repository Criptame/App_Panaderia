package com.example.app_panaderia.model.data

import androidx.room.ColumnInfo

data class CategoriaConteo(
    @ColumnInfo(name = "categoria")
    val categoria: String,

    @ColumnInfo(name = "total")
    val total: Int
)