package com.ctt.first_project_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ctt.first_project_mobile.model.Usuario

class MainActivity : AppCompatActivity() {
//    ciclos de vida -> onCreate
//    CICLOS DE VIDA: Nasce (Comeca), Cresce (Executa) e Morre (Encerra)

    private val CICLO_VIDA ="CICLO_VIDA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        para saber se esta linkado corretamente
//        R -> res
        setContentView(R.layout.activity_main)

        Log.e(CICLO_VIDA, "App em onCREATE")

        val mensagem = "Usuario cadastrado com sucesso!"

//        Maneiras de referenciar componentes xml na sua activity:
//        1. findViewById, convencional desde o Java
//        2. synthetic do Kotlin, recurso que importa automaticamente
//        3. view binding, recurso novo disponivel no android jetpack

//        para achar a view com a qual esta relacionada -> pelo id
//        colocar o tipo de elemento e o id dele
//        para referenciar, achar o componente no xml e integrar com kotlin
        val buttonCadastrar = findViewById<Button>(R.id.btnCadastrar)
        val nomeUsuario = findViewById<EditText>(R.id.edtNomeUsuario)
        val idadeUsuario = findViewById<EditText>(R.id.edtIdadeUsuario)

        var contador = 0

//        quando clicar -> event listener
        buttonCadastrar.setOnClickListener{
            val nomeDigitado = nomeUsuario.text.toString()
            val idadeDigitada = idadeUsuario.text.toString()

            if (nomeDigitado.isEmpty()) {
                if (idadeDigitada.isEmpty()) {
                    idadeUsuario.error = "Voce ainda nao nasceu?"
                    nomeUsuario.error = "Nao existe nome vazio, ne?"
                } else {
                    nomeUsuario.error = "Nao existe nome vazio, ne?"
                }
//                exibirMensagemErro()
//                ao inves de utilizar um metodo, trabalhar com componente do Kotlin

            } else {
                if (idadeDigitada.isEmpty()) {
                    idadeUsuario.error = "Voce ainda nao nasceu?"
                } else {
//                    esta incrementando depois o contador -> contador++
//                esta incrementando antes o contador -> ++contador
                    val usuario = Usuario(++contador, nomeDigitado, idadeDigitada.toInt())
                    exibirUsuario(usuario)
//                exibirMensagem(nomeDigitado)
                }
            }

//            nomeDigitado nao eh string, eh editavel
//            extends text but is a Editable
//            exibirMensagem(mensagem)
        }

//        existe outra forma de referenciar via kotlin
//        btnCadastrar

        // todo nosso codigo esta aqui
    }

    fun exibirMensagem(nome : String) {
//        context -> onde voce esta, da onde que esta vindo esse makeText -> da app, dessa tela?
        Toast.makeText(this, "Boas vindas, $nome", Toast.LENGTH_SHORT).show()
    }
    fun exibirUsuario(usuario: Usuario) {
//        para trabalhar com logs -> console
        Log.e("USUARIO", usuario.toString())

        Toast.makeText(this, "Boas vindas, ${usuario.nome}. Seu id eh ${usuario.id}.", Toast.LENGTH_SHORT).show()
    }


    fun exibirMensagemErro() {
        Toast.makeText(this, "Por favor, insira o nome do usuario", Toast.LENGTH_SHORT).show()
    }

//    app iniciando

    override fun onStart() {
        super.onStart()
        Log.e(CICLO_VIDA, "App em onStart")
    }

//    app em execucao

    override fun onResume() {
        super.onResume()
        Log.e(CICLO_VIDA, "App em onResume")
    }

//     quando sair da app, abrir outra app, etc

    override fun onStop() {
        super.onStop()
        Log.e(CICLO_VIDA, "App em onStop")
    }

//    quando volta pra app, depois de estar em outra app

    override fun onRestart() {
        super.onRestart()
        Log.e(CICLO_VIDA, "App em onRestart")
    }

//    quando fecha a aplicacao

    override fun onDestroy() {
        super.onDestroy()
        Log.e(CICLO_VIDA, "App em onDestroy")
    }
}