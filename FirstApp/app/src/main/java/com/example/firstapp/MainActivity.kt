package com.example.c1_01202056_natan_xavier_da_silva

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.firstapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun apurar(view: View) {
        val etNomeCandidato1: EditText = findViewById(R.id.etNomeCandidato1)
        val etNomeCandidato2: EditText = findViewById(R.id.etNomeCandidato2)
        val etVotosCandidato1: EditText = findViewById(R.id.etVotosCandidato1)
        val etVotosCandidato2: EditText = findViewById(R.id.etVotosCandidato2)

        if (camposValidos(
                etVotosCandidato1,
                etVotosCandidato2,
                etNomeCandidato1,
                etNomeCandidato2
            )
        ) {

            findViewById<Button>(R.id.btn_apurar_votacao).visibility = View.GONE
            findViewById<Button>(R.id.btn_nova_votacao).visibility = View.VISIBLE
            var vencedor: String = ""
            val qtdVotosTotais: Int =
                etVotosCandidato1.text.toString().toInt() + etVotosCandidato2.text.toString()
                    .toInt()
            var percentualDeVotos: Double
            if (etVotosCandidato1.text.toString().toInt() > etVotosCandidato2.text.toString()
                    .toInt()
            ) {
                vencedor = etNomeCandidato1.text.toString()
                percentualDeVotos =
                    (etVotosCandidato1.text.toString().toDouble() / qtdVotosTotais) * 100
            } else {
                vencedor = etNomeCandidato2.text.toString()
                percentualDeVotos =
                    (etVotosCandidato2.text.toString().toDouble() / qtdVotosTotais) * 100
            }

            mostrarResultado(vencedor, percentualDeVotos, etVotosCandidato1, etVotosCandidato2)
        }



    }

    private fun mostrarResultado(
        vencedor: String,
        percentualDeVotos: Double,
        etVotosCandidato1: EditText,
        etVotosCandidato2: EditText
    ) {
        val tvResultado1: TextView = findViewById(R.id.tvResultado1)
        val tvResultado2: TextView = findViewById(R.id.tvResultado2)
        if (etVotosCandidato1.text.toString().toInt() > etVotosCandidato2.text.toString()
                .toInt()
        ) {
            tvResultado1.text =
                "${vencedor}  venceu com ${"%.0f".format(percentualDeVotos)}% dos votos"
            findViewById<TextView>(R.id.tvResultado1).visibility = View.VISIBLE

        } else {
            tvResultado2.text =
                "${vencedor}  venceu com ${"%.0f".format(percentualDeVotos)}% dos votos"
            findViewById<TextView>(R.id.tvResultado2).visibility = View.VISIBLE


        }
    }

     fun novaVotacao(view: View) {
        findViewById<Button>(R.id.btn_nova_votacao).visibility = View.GONE
        findViewById<Button>(R.id.btn_apurar_votacao).visibility = View.VISIBLE
        findViewById<TextView>(R.id.tvResultado1).visibility = View.GONE
        findViewById<TextView>(R.id.tvResultado2).visibility = View.GONE

         findViewById<EditText>(R.id.etVotosCandidato1).setText("")
         findViewById<EditText>(R.id.etVotosCandidato2).setText("")
         findViewById<EditText>(R.id.etNomeCandidato1).setText("")
         findViewById<EditText>(R.id.etNomeCandidato2).setText("")



    }


    private fun camposValidos(
        etVotosCandidato1: EditText,
        etVotosCandidato2: EditText,
        etNomeCandidato1: EditText,
        etNomeCandidato2: EditText
    ): Boolean {
        if (etVotosCandidato1.text.isEmpty() || etVotosCandidato1.text.toString().toInt() <= 0) {
            etVotosCandidato1.error = "Preencha a quantidade de votos válidos!"
            return false
        } else if (etVotosCandidato2.text.isEmpty() || etVotosCandidato2.text.toString()
                .toInt() <= 0
        ) {
            etVotosCandidato2.error = "Preencha a quantidade de votos válidos!"
            return false
        } else if (etNomeCandidato1.text.isEmpty() || etNomeCandidato1.text.toString().length < 2) {
            etNomeCandidato1.error = "Preencha o nome do candidato!"
            return false
        } else if (etNomeCandidato2.text.isEmpty() || etNomeCandidato2.text.toString().length < 2) {
            etNomeCandidato2.error = "Preencha o nome do candidato!"
            return false
        } else if (etVotosCandidato1.text.toString().toInt() == etVotosCandidato2.text.toString()
                .toInt()
        ) {
            etVotosCandidato1.error = "Preencha a quantidade de votos válidos!"
            etVotosCandidato2.error = "Preencha a quantidade de votos válidos!"
            return false

        }

        return true

    }
}