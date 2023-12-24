package com.example.restaurantapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var cuentaMesa: CuentaMesa
    private lateinit var textViewNombrePlato1: TextView
    private lateinit var textViewPrecioPlato1: TextView
    private lateinit var textViewNombrePlato2: TextView
    private lateinit var textViewPrecioPlato2: TextView
    private lateinit var textViewNombrePlato3: TextView
    private lateinit var textViewPrecioPlato3: TextView
    private lateinit var textViewTotalSinPropina: TextView
    private lateinit var textViewPropina: TextView
    private lateinit var textViewTotalConPropina: TextView
    private lateinit var switchPropina: Switch
    private lateinit var editTextCantidadCazuela: EditText
    private lateinit var editTextCantidadEmpanadas: EditText
    private lateinit var editTextCantidadPorotos: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewNombrePlato1 = findViewById(R.id.textViewPlato1)
        textViewPrecioPlato1 = findViewById(R.id.textViewPrecio1)
        textViewNombrePlato2 = findViewById(R.id.textViewPlato2)
        textViewPrecioPlato2 = findViewById(R.id.textViewPrecio2)
        textViewNombrePlato3 = findViewById(R.id.textViewPlato3)
        textViewPrecioPlato3 = findViewById(R.id.textViewPrecio3)
        textViewTotalSinPropina = findViewById(R.id.textViewTotal)
        textViewPropina = findViewById(R.id.textViewPropina)
        textViewTotalConPropina = findViewById(R.id.textView14)

        switchPropina = findViewById(R.id.switch1)

        editTextCantidadCazuela = findViewById(R.id.editTextCantidadCazuela)
        editTextCantidadEmpanadas = findViewById(R.id.editTextCantidadEmpanadas)
        editTextCantidadPorotos = findViewById(R.id.editTextCantidadPorotos)

        cuentaMesa = CuentaMesa(aceptaPropina = true, mutableListOf())

        editTextCantidadCazuela.addTextChangedListener(CantidadTextWatcher())
        editTextCantidadEmpanadas.addTextChangedListener(CantidadTextWatcher())
        editTextCantidadPorotos.addTextChangedListener(CantidadTextWatcher())

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            cuentaMesa.aceptaPropina = isChecked
            actualizarUI()
        }

        actualizarUI()
    }

    private fun actualizarUI() {
        inicializarPlatos()

        textViewNombrePlato1.text = cuentaMesa.items.getOrNull(0)?.itemMenu?.nombre ?: ""
        textViewPrecioPlato1.text = cuentaMesa.items.getOrNull(0)?.itemMenu?.precioFormateado() ?: ""
        textViewNombrePlato2.text = cuentaMesa.items.getOrNull(1)?.itemMenu?.nombre ?: ""
        textViewPrecioPlato2.text = cuentaMesa.items.getOrNull(1)?.itemMenu?.precioFormateado() ?: ""
        textViewNombrePlato3.text = cuentaMesa.items.getOrNull(2)?.itemMenu?.nombre ?: ""
        textViewPrecioPlato3.text = cuentaMesa.items.getOrNull(2)?.itemMenu?.precioFormateado() ?: ""

        val totalSinPropina = cuentaMesa.calcularTotalSinPropina()
        val propina = if (cuentaMesa.aceptaPropina) cuentaMesa.calcularpropina() else 0.0
        val totalConPropina = totalSinPropina + propina

        textViewTotalSinPropina.text = totalSinPropina.precioFormateado()
        textViewPropina.text = propina.precioFormateado()
        textViewTotalConPropina.text = totalConPropina.precioFormateado()
    }

    private fun inicializarPlatos() {
        cuentaMesa.items.clear()

        val itemMenu1 = ItemMenu("Cazuela", 10000.0)
        val itemMenu2 = ItemMenu("Empanadas de horno", 8500.0)
        val itemMenu3 = ItemMenu("Porotos", 5000.0)

        cuentaMesa.agregarItem(ItemMesa(obtenerCantidad(editTextCantidadCazuela), itemMenu1))
        cuentaMesa.agregarItem(ItemMesa(obtenerCantidad(editTextCantidadEmpanadas), itemMenu2))
        cuentaMesa.agregarItem(ItemMesa(obtenerCantidad(editTextCantidadPorotos), itemMenu3))
    }

    private fun obtenerCantidad(editText: EditText): Int {
        return editText.text.toString().toIntOrNull() ?: 0
    }

    private inner class CantidadTextWatcher : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            actualizarUI()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun Double.precioFormateado(): String {
        val formatoChileno = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formatoChileno.format(this)
    }
}
