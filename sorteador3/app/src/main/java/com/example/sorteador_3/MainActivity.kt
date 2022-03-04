package com.example.sorteador_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.content.res.AppCompatResources
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private var saldoAtual: Double = 120.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun atualizarCarteira() {
        val tvCarteira: TextView = findViewById(R.id.tv_carteira)
        tvCarteira.text = saldoAtual.toString()

        if (saldoAtual <= 0.0) {
            tvCarteira.setTextColor(
                AppCompatResources.getColorStateList(
                    baseContext,
                    R.color.primary_red
                )
            )
        }
    }

    fun validarResultado(isAllWin: Boolean, numeroSorteado: Int, etPalpite: Int) {
        var valorAposta = findViewById<EditText>(R.id.et_valor_aposta).text.toString().toDouble()
        if (isAllWin) {
            valorAposta = saldoAtual
        }

        if (numeroSorteado == etPalpite) {
            valorAposta *= 2
            saldoAtual += valorAposta
            findViewById<TextView>(R.id.tv_resultado).text =
                "Parabéns, você acertou! + $valorAposta"
        } else {
            findViewById<TextView>(R.id.tv_resultado).text =
                "Infelizmente você errou! - $valorAposta"
            saldoAtual -= valorAposta
        }

        findViewById<TextView>(R.id.tv_carteira).text = saldoAtual.toString()
    }

    fun apostar(view: View) {
        val etPalpite: EditText = findViewById(R.id.et_palpite)
        val etAposta: EditText = findViewById(R.id.et_valor_aposta)
        val idNome: String = view.getResources().getResourceName(view.getId());
        var isAllWin: Boolean = false
        if(idNome == "com.example.sorteador_3:id/btn_all_win"){
            isAllWin = true
        }

        if (camposValidos(etPalpite, etAposta, isAllWin)) {
            val numeroSorteado = sortear()
            validarResultado(isAllWin, numeroSorteado, etPalpite.text.toString().toInt())
            Toast.makeText(baseContext, numeroSorteado.toString(), Toast.LENGTH_LONG).show()
            atualizarImagemSorteio(numeroSorteado)
            atualizarCarteira()
            validarParada()
        }
    }

    private fun validarParada() {
        val swParar: Switch = findViewById(R.id.sw_parar)
        if (swParar.isChecked) {
            if (saldoAtual <= 0.0) {
                findViewById<Button>(R.id.btn_apostar).visibility = View.GONE
                findViewById<Button>(R.id.btn_all_win).visibility = View.GONE
            }
        }
    }

    private fun atualizarImagemSorteio(numeroSorteado: Int) {
        val imagem =
            when (numeroSorteado) {
                1 -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_1)
                2 -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_2)
                3 -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_3)
                4 -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_4)
                5 -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_5)
                else -> AppCompatResources.getDrawable(baseContext, R.drawable.ic_dado_6)
            }

        findViewById<ImageView>(R.id.tv_dado).setImageDrawable(imagem)

    }

    private fun sortear(): Int = (Math.random() * 6 + 1).toInt()

    private fun camposValidos(etPalpite: EditText, etAposta: EditText, isAllWin: Boolean): Boolean {
        if (etPalpite.text.toString().isEmpty()) {
            etPalpite.error = "Valor inválido!"
            return false
        } else if (etPalpite.text.toString().toInt() !in 1..6) {
            etPalpite.error = "Valor deve estar entre 1 e 6!"
            return false
        } else if (!isAllWin) {
            if (etAposta.text.toString().isEmpty()) {
                etAposta.error = "Valor inválido!"
                return false
            } else if (etAposta.text.toString().toDouble() <= 0.0) {
                etAposta.error = "Valor mínimo de aposta: R$0.1"
                return false
            } else if (etAposta.text.toString().toDouble() > saldoAtual) {
                etAposta.error = "Saldo insuficiente!"
                return false
            }
        }

        return true
    }
}