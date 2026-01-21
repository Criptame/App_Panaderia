package com.example.app_panaderia.model

class Pan{
    var id: String = ""
    var nombre: String = ""
    var descripcion: String = ""
    var precio: Double = 0.0
    var imagenUrl: String = ""

    constructor()

    constructor(id: String, nombre: String, descripcion: String, precio: Double, imagenUrl: String) {
        this.id = id
        this.nombre = nombre
        this.descripcion = descripcion
        this.precio = precio
        this.imagenUrl = imagenUrl
    }
}