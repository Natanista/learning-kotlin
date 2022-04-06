package com.example.my_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var precoIndividual: Double = 0.0
    private var nomeProduto: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun comprarBanana(view: View) {
        nomeProduto = "Banana"
        precoIndividual += 0.5

        irTela2()

    }

    fun comprarPera(view: View) {
        nomeProduto = "Pera"
        precoIndividual += 1.5

        irTela2()


    }

    fun comprarMaca(view: View) {
        nomeProduto = "Maca"
        precoIndividual += 0.75

        irTela2()


    }

    fun comprarUva(view: View) {
        nomeProduto = "Uva"
        precoIndividual += 0.1

        irTela2()

    }


    fun irTela2() {

        val nomeUsuario: String = findViewById<TextView>(R.id.etNome).text.toString()
        val quantidade: Int = findViewById<TextView>(R.id.etQuantidade).text.toString().toInt()

        if (camposValidos(findViewById(R.id.etNome), findViewById(R.id.etQuantidade))) {
            val tela2: Intent = Intent(baseContext, Tela2::class.java)

            tela2.putExtra("nomeUsuario", nomeUsuario)
            tela2.putExtra("quantidade", quantidade)
            tela2.putExtra("nomeProduto", nomeProduto)
            tela2.putExtra("precoIndividual", precoIndividual)

            startActivity(tela2)
        }

    }

    fun camposValidos(
        etNome: EditText,
        etQuantidade: EditText,

        ): Boolean {
        if (etNome.text.toString().isEmpty()) {
            etNome.error = "Preencha um nome válido!"
            return false
        } else if (
            etQuantidade.text.toString().toInt() <= 0 ||
            etQuantidade.text.isEmpty()
        ) {
            etQuantidade.error = "Preencha uma quantidade válida!"
            return false
        }

        return true
    }
}