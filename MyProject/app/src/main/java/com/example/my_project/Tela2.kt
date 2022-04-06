package com.example.my_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class Tela2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela2)
        val nomeUsuario = intent.getStringExtra(
            "nomeUsuario"
        )
        val nomeProduto = intent.getStringExtra(
            "nomeProduto"
        )
        val quantidade: Int = intent.getIntExtra("quantidade", 0)
        val precoIndividual: Double = intent.getDoubleExtra("precoIndividual", 0.0)

        findViewById<TextView>(R.id.tvNome).text = "Olá ${nomeUsuario},"
        findViewById<TextView>(R.id.tvValorFinal).text = "O produto ${nomeProduto}, custa R$${precoIndividual} a unidade, sendo assim sua compra sairá por R$${quantidade * precoIndividual}"
    }

    fun voltar(view: View){
        startActivity(Intent(baseContext, MainActivity::class.java))
    }
}