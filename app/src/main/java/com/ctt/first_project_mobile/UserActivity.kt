package com.ctt.first_project_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ctt.first_project_mobile.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

//    Bundle -> infos adicionais
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        para receber uma intent -> intent.extras.get("CHAVE UTILIZADA")
        val nomeUsuario = intent.extras?.get("NOME")
        val idadeUsuario = intent.extras?.get("IDADE")

        binding.txtDadosUsuario.text = "Oi, $nomeUsuario, voce tem $idadeUsuario anos"
    }
}
