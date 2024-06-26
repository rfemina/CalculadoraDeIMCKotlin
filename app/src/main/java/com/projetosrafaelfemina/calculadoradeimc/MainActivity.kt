package com.projetosrafaelfemina.calculadoradeimc

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.projetosrafaelfemina.calculadoradeimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bt_calculate = binding.btCalculate
        val message = binding.txtResult

        bt_calculate.setOnClickListener {
            val editWeight = binding.editWeight.text.toString()
            val editHeight = binding.editHeight.text.toString()

            if (editHeight.isEmpty()) {
                message.setText("Informe a sua Altura")
            } else if (editWeight.isEmpty()) {
                message.setText("Informe o seu Peso")
            } else {
                CalculoDeIMC();
            }
        }
    }

    private fun CalculoDeIMC() {
        val weightID = binding.editWeight
        val heightID = binding.editHeight

        val weight = java.lang.Float.parseFloat(weightID.text.toString())
        val height = java.lang.Float.parseFloat(heightID.text.toString())
        val result = binding.txtResult
        val imc = weight / (height * height)


        val Message = when {
            imc <= 18.5 -> "Peso Baixo"
            imc <= 24.9 -> "Peso Normal"
            imc <= 29.9 -> "Sobrepeso"
            imc <= 34.9 -> "Obesidade (Grau 1)"
            imc <= 39.9 -> "Obesidade (Grau 2)"
            else -> "Obesidade Mórbida (Grau 3)"
        }

        imc.toString()
        result.setText("IMC: $imc \n $Message")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.reset -> {
                val clearEditWeight = binding.editWeight
                val clearEditHeight = binding.editHeight
                val clearMessage = binding.txtResult

                clearEditWeight.setText("")
                clearEditHeight.setText("")
                clearMessage.setText("")
            }
        }

        return super.onOptionsItemSelected(item)
    }
}