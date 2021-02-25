package com.ctt.first_project_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserActivity : AppCompatActivity() {

    val userData = findViewById<TextView>(R.id.txtDadosUsuario)

//    Bundle -> infos adicionais
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

//        para receber uma intent -> intent.extras.get("CHAVE UTILIZADA")
        val nomeUsuario = intent.extras?.get("NOME")
        val idadeUsuario = intent.extras?.get("IDADE")

    userData.text = "Oi, $nomeUsuario, voce tem $idadeUsuario anos"
    }
}