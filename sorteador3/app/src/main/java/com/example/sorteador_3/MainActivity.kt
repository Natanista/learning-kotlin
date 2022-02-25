package com.example.sorteador_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var saldoAtual: Double = 120.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun validarResultado(numeroSorteado: Int, etPalpite: Int){
        var valorAposta = findViewById<EditText>(R.id.et_valor_aposta).text.toString().toDouble()

        if(numeroSorteado == etPalpite){
            valorAposta *= 2
            saldoAtual += valorAposta
        }else{
            saldoAtual -= valorAposta
        }

        findViewById<TextView>(R.id.tv_carteira).text = saldoAtual.toString()
    }

    fun apostar(view: View){
        val etPalpite: EditText = findViewById(R.id.et_palpite)
        val etAposta: EditText = findViewById(R.id.et_valor_aposta)

        if(camposValidos(etPalpite, etAposta)){
            val numeroSorteado = sortear()
            validarResultado(numeroSorteado, etPalpite.text.toString().toInt())
            Toast.makeText(baseContext, numeroSorteado.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun sortear(): Int = (Math.random() * 6 + 1).toInt()

    private fun camposValidos(etPalpite: EditText, etAposta: EditText): Boolean {
    if(etPalpite.text.toString().isEmpty() ){
        etPalpite.error = "Valor inválido!"
        return false
    }else if( etPalpite.text.toString().toInt() !in 1..6){
        etPalpite.error = "Valor deve estar entre 1 e 6!"
        return false
    }else if(etAposta.text.toString().isEmpty()){
        etAposta.error = "Valor inválido!"
        return false
    }else if(etAposta.text.toString().toDouble() <= 0.0){
        etAposta.error = "Valor mínimo de aposta: R$0.1"
        return false
    }else if(etAposta.text.toString().toDouble() > saldoAtual){
        etAposta.error = "Saldo insuficiente!"
        return false
    }

        return true
    }
}