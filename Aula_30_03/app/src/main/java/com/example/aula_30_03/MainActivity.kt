package com.example.aula_30_03

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        container = findViewById(R.id.ll_frases_container)
        findViewById<Button>(R.id.btn_comprar_fruta).setOnClickListener { view ->
            comprarFruta(view)
        }
        findViewById<Button>(R.id.btn_comprar_legume).setOnClickListener { view ->
            comprarLegume(view)
        }
        findViewById<Button>(R.id.btn_zerar).setOnClickListener { view ->
            zerar(view)
        }
    }

    fun comprarFruta(view: View) {
        val t = TextView(baseContext)
        t.text = getString(R.string.comprar_fruta)
        container.addView(t)
        atualizarTotal(20.00)
    }

    fun comprarLegume(view: View) {
        val t = TextView(baseContext)
        t.text = getString(R.string.comprar_legume)
        t.setTextColor(ContextCompat.getColor(baseContext, R.color.purple_200))
        container.addView(t)
        atualizarTotal(10.00)
    }

    private fun atualizarTotal(valorFruta: Double) {
        total += valorFruta
        val t = TextView(baseContext)
        t.text = getString(R.string.total_compra, total)
        t.setTextColor(ContextCompat.getColor(baseContext, R.color.primary_green))
        container.addView(t)
    }

    fun zerar(view: View) {
        total = 0.0
        container.removeAllViews()
    }
}