package com.example.imcapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun calcular(view: View) {
        val etNome: EditText = findViewById(R.id.etNome)
        val etIdade: EditText = findViewById(R.id.etIdade)
        val etAltura: EditText = findViewById(R.id.etAltura)
        val etPeso: EditText = findViewById(R.id.etPeso)

        if (camposValidos(etAltura, etIdade, etNome, etPeso)) {
            val altura: Double = etAltura.text.toString().toDouble() / 100
            val alturaAoQuadrado: Double = altura * altura
            val imc: Double = etPeso.text.toString().toDouble() / alturaAoQuadrado

            mostrarResultados(etNome.text.toString(), imc, etIdade.text.toString().toInt())
        }
    }

    fun mostrarResultados(etNome: String, imc: Double, idade: Int) {
        val tvIMC: TextView = findViewById(R.id.tvIMC)
        val resultadoFaixaEtaria: String = obterResultadoFaixaEtaria(imc, idade)
        tvIMC.text = "${etNome},seu IMC é de ${"%.2f".format(imc)}"
        findViewById<TextView>(R.id.tvConclusao).text = "Para esta idade seu imc esta ${resultadoFaixaEtaria}"



    }

    private fun obterResultadoFaixaEtaria(imc: Double, idade: Int): String {
        if (idade < 18) {
            return if (imc < 20) "bom" else "ruim"
        } else if(idade < 60){
            return if( imc < 22) "bom" else "ruim"
        } else {
            return if(imc < 25) "bom" else "ruim"
        }
    }

    fun camposValidos(
        etAltura: EditText,
        etIdade: EditText,
        etNome: EditText,
        etPeso: EditText
    ): Boolean {
        if (etNome.text.toString().isEmpty()) {
            etNome.error = "Preencha um nome válido!"
            return false
        } else if (
            etIdade.text.toString().toInt() <= 0 ||
            etIdade.text.isEmpty()
        ) {
            etIdade.error = "Preencha uma idade válida!"
            return false
        } else if (
            etAltura.text.toString().toInt() <= 0 ||
            etAltura.text.isEmpty()
        ) {
            etAltura.error = "Preencha uma altura válida!"
            return false
        } else if (
            etPeso.text.toString().toDouble() <= 0 ||
            etPeso.text.isEmpty()
        ) {
            etPeso.error = "Preencha um peso valido!"
            return false
        }
        return true
    }


}