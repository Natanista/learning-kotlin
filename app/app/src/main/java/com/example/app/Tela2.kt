package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Tela2 : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var tvErroSenha: TextView
    private lateinit var etConfirmPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_tela2)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        tvErroSenha = findViewById(R.id.tvErroSenha)
        etConfirmPassword = findViewById(R.id.et_confirme_password)

        val email = intent.getStringExtra(
            "email"
        )


        val senha = intent.getStringExtra(
            "senha"
        )

        etEmail.setText(email)
        etPassword.setText(senha)



    }


    fun voltar(view: View) {
        startActivity(Intent(baseContext, MainActivity::class.java))
    }

    fun cadastrar(view: View) {
        if (tvErroSenha.visibility.equals(View.VISIBLE)) {
            tvErroSenha.visibility = View.GONE
        }
        if (camposValidos(etEmail, etPassword, etConfirmPassword)) {
            Toast.makeText(
                baseContext,
                "Cadastro efetuado com sucesso!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    private fun camposValidos(
        etEmail: EditText,
        etPassword: EditText,
        etConfirmPassword: EditText
    ): Boolean {
        if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Campo não pode estar vazio"
            return false
        } else if (etPassword.text.toString().isEmpty()) {
            etPassword.error = "Campo não pode estar vazio"
            return false
        } else if (etConfirmPassword.text.toString().isEmpty()) {
            etConfirmPassword.error = "Campo não pode estar vazio"
            return false
        } else if (!(etConfirmPassword.text.toString().equals(etPassword.text.toString()))) {
            tvErroSenha.visibility = View.VISIBLE
            return false
        }
        return true
    }


}