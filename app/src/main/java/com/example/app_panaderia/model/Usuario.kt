package com.example.app_panaderia.model

class Usuario {
    var id: String = ""
    var nombre: String = ""
    var email: String = ""
    var rol: String = ""

    constructor()

    constructor(id: String, nombre: String, email: String, rol: String) {
        this.id = id
        this.nombre = nombre
        this.email = email
        this.rol = rol
    }
}