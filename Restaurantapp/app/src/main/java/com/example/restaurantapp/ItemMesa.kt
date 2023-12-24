package com.example.restaurantapp

class ItemMesa {
    var cantidad: Int = 0
    var itemMenu: ItemMenu? = null

    constructor(cantidad: Int, itemMenu: ItemMenu?) {
        this.cantidad = cantidad
        this.itemMenu = itemMenu
    }

    fun calcularSubtotal(): Double {
        return cantidad * (itemMenu?.precio ?: 0.0)
    }
}
