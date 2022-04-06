package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvErroLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        tvErroLogin = findViewById(R.id.tvErroLogin)
    }

    fun entrar(view: View) {

        validarLogin(etEmail, etPassword)
    }

    private fun validarLogin(etEmail: EditText, etPassword: EditText) {
        if (etEmail.text.toString().equals("admin@admin.com") && etPassword.text.toString()
                .equals("123456")
        ) {
            if (tvErroLogin.visibility.equals(View.VISIBLE)) {
                tvErroLogin.visibility = View.GONE
            }
            Toast.makeText(
                baseContext,
                "Login efetuado com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            tvErroLogin.visibility = View.VISIBLE
        }
    }





    fun cadastrar(view: View) {
        val tela2: Intent = Intent(baseContext, Tela2::class.java)
        tela2.putExtra("email", etEmail.text.toString())
        tela2.putExtra("senha", etPassword.text.toString())
        startActivity(tela2)    }



}