package com.example.restaurantapp

class CuentaMesa {
    var aceptaPropina: Boolean = true
    var items: MutableList<ItemMesa> = mutableListOf()

    constructor(aceptaPropina: Boolean, items: MutableList<ItemMesa>) {
        this.aceptaPropina = aceptaPropina
        this.items = items
    }

    fun agregarItem(itemMesa: ItemMesa) {
        items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Double {
        var total = 0.0
        for (item in items) {
            total += item.calcularSubtotal()
        }
        return total
    }

    fun calcularpropina(): Double {
        val totalSinPropina = calcularTotalSinPropina()
        val propina = totalSinPropina * 0.10  // Calcula el 10% del total sin propina
        return propina
    }

    fun calcularTotalConPropina(): Double {
        val totalSinPropina = calcularTotalSinPropina()
        val propina = calcularpropina()
        val totalConPropina = totalSinPropina + propina
        return totalConPropina
    }
}
