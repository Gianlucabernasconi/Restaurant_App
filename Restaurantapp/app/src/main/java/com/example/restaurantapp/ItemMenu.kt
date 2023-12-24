package com.example.restaurantapp

import java.text.NumberFormat
import java.util.Locale

class ItemMenu {
    var nombre: String = ""
    var precio: Double = 0.0

    constructor(nombre: String, precio: Double) {
        this.nombre = nombre
        this.precio = precio
    }


    // Método para formatear el precio como moneda chilena
    fun precioFormateado(): String {
        val formatoChileno = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formatoChileno.format(precio)
    }
}
