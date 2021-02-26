package com.ctt.first_project_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ctt.first_project_mobile.databinding.ActivityUserBinding
import com.ctt.first_project_mobile.model.Usuario
import kotlinx.android.synthetic.main.activity_main.*

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

//    Bundle -> infos adicionais
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//    String nomeUsuario = getIntent().getExtras().get("NOME")
//        para receber uma intent -> intent.extras.get("CHAVE UTILIZADA")
//        val nomeUsuario = intent.extras?.get("NOME")
//        val idadeUsuario = intent.extras?.get("IDADE")

//    como agora estamos passando o objeto
//    ele nao sabe que o usuario eh do tipo usuario, so sabe que eh parcel
//    CASTING para USUARIO
    val usuario = intent.extras?.get("USUARIO") as Usuario

//        binding.txtDadosUsuario.text = "Oi, ${usuario.nome}, voce tem ${usuario.idade} anos"
//    \n eh quebra de linha em uma String

    binding.txtDadosUsuario.text = "ID: ${usuario.id}," +
            "\nNome: ${usuario.nome}," +
            "\nIdade: ${usuario.idade}"

    imgUsuario.setImageBitmap(usuario.foto)

//    evitar processamento desnecessario -> verificacao
        usuario.foto?.let{
    //    it = tudo que eu falei antes do .let
    //    imgUsuario.setImageBitmap(usuario.foto)
            imgUsuario.setImageBitmap(it)
        }
    }
}
