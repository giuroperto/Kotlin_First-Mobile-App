package com.ctt.first_project_mobile

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.ctt.first_project_mobile.model.Usuario

class MainActivity : AppCompatActivity() {
//    ciclos de vida -> onCreate
//    CICLOS DE VIDA: Nasce (Comeca), Cresce (Executa) e Morre (Encerra)

    private val CICLO_VIDA ="CICLO_VIDA"
//    BOAS PRATICAS, deixar as variaveis utilizadas na Activity aqui fora
//    Mas esses campos so vao existir quando a Activity for criada -> no onCreate
//    declarar sem inicializar -> lateinit

    private lateinit var buttonCadastrar : Button
    private lateinit var nomeUsuario : EditText
    private lateinit var idadeUsuario : EditText
    private lateinit var fotoUsuario : ImageView

//    boas praticas -> lateinit significa que vai ter um valor atribuido futuramente, mas a foto nao, eh opcional, por isso nao utilizamos e ja atribuimos um valor a ela
    private var foto : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        all our code is here

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
        buttonCadastrar = findViewById(R.id.btnCadastrar)
        nomeUsuario = findViewById(R.id.edtNomeUsuario)
        idadeUsuario = findViewById(R.id.edtIdadeUsuario)
        fotoUsuario = findViewById(R.id.imgUsuario)

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

                    val usuario = Usuario(++contador, nomeDigitado, idadeDigitada.toInt(), foto)
                    exibirUsuario(usuario)
//                  exibirMensagem(nomeDigitado)

                }
            }

//            nomeDigitado nao eh string, eh editavel
//            extends text but is a Editable
//            exibirMensagem(mensagem)
        }

        fotoUsuario.setOnClickListener{
            abrirCamera()
        }

//        existe outra forma de referenciar via kotlin
//        btnCadastrar
    }

    fun abrirCamera() {
        val CAMERA_REQUEST_CODE = 12345
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            utilizar algo ou recurso que nao tenha no celular do usuario -> recurso nao existe
//            se chamar a activity direto -> null
//            se der null -> app crash
//            portanto checa se existe uma activity de camera nessa action para nosso app
//            packageManager -> gerenciador de arquivos/pacotes do android -> verificar se existe a activity para a acao
//            por ser android, cada fabricante lida de forma diferente -> cada uma modifica a sua maneira e pode levar a erros

//          quando o usuario nao da a permissao, teria que fazer uma nova validacao para o permissionamento

        if (cameraIntent.resolveActivity(packageManager) != null) {
//                Inicie a camera
//                startActivity nao vai funcionar pois quero que me retorne algo -> foto
//                startActivity(cameraIntent)
//                quando queremos o resultado dessa activity
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        } else {
            Toast.makeText(this, "Opa, alguma coisa aconteceu... tente novamente!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        por boas praticas, foi declarado do lado de fora
//        val userImg = findViewById<ImageView>(R.id.imgUsuario)

//        Verificando se o dado que esta vindo e do tipo camera que eu pedi, conforme determinado na funcao abrir camera e constante CAMERA_REQUEST_CODE
//        Verificando se nao houve erros durante a tirada da foto
        if (requestCode == 12345 && resultCode == RESULT_OK) {
//            val imagem = data?.extras?.get("data") as Bitmap?

            foto = data?.extras?.get("data") as Bitmap

//            se der null, nao cai aqui -> se nao for bitmap ou algum erro ocorrer
//            o null safety nao funcionou, entao deixar sem
//            imagem?.let {
//                userImg.setImageBitmap(imagem)
//            }

            fotoUsuario.setImageBitmap(foto)
        }
    }

    fun exibirMensagem(nome : String) {
//        context -> onde voce esta, da onde que esta vindo esse makeText -> da app, dessa tela?
        Toast.makeText(this, "Boas vindas, $nome", Toast.LENGTH_SHORT).show()
    }

    fun exibirUsuario(usuario: Usuario) {
//        para trabalhar com logs -> console
//        Log.e("USUARIO", usuario.toString())

//        Toast.makeText(this, "Boas vindas, ${usuario.nome}. Seu id eh ${usuario.id}.", Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Usuario cadastrado com sucesso", Toast.LENGTH_SHORT).show()

        redirecionar(usuario)
    }


    fun exibirMensagemErro() {
        Toast.makeText(this, "Por favor, insira o nome do usuario", Toast.LENGTH_SHORT).show()
    }

    fun redirecionar(usuario: Usuario) {
//        Intent(aonde eu estou (CONTEXT this), para onde eu vou (CLASS)
//        CUIDADO! A classe precisa ser ::class.java pois a Intent pede uma (C)lass
//        Se pudesse passar uma classe KOTLIN, poderia chamar atraves do ::class
//        Porem o parametro seria do tipo (KC)lass

//        precisa de uma chava para mandar para a proxima activity
//        vamos utilizar do extras para enviar, diferente do utilizado antes para get

//        val chaveNomeUsuario = "NOME"
//        val chaveIdadeUsuario = "IDADE"

//        criar apenas uma chave para o usuario pois vamos passar o objeto por inteiro
        val chaveUsuario = "USUARIO"

        val destinoActivity = Intent(this, UserActivity::class.java)
//        ao inves de passar como extra cada propriedade separadamente -> passar o objeto
//        destinoActivity.putExtra(chaveNomeUsuario, usuario.nome)
//        destinoActivity.putExtra(chaveIdadeUsuario, usuario.idade)

//        classe usuario eh um tipo de dado vindo do meu projeto
//        android nao sabe o tipo de dado que eh
//        para resolver esse problema -> funcao do Kotlin parsialize
//        vai pegar a classe e transformar para algo que o android entenda ("cryptographing")
//        idem a serialize do java
//        converte o formato por baixo dos panos para o android entender que eh uma classe personalizada
        destinoActivity.putExtra(chaveUsuario, usuario)

//        Inicia uma nova Activity
        startActivity(destinoActivity)

//        Encerra Activity ATUAL (MainActivity)
        finish()
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

//    quando clica para voltar a pagina

    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "Tchau!", Toast.LENGTH_SHORT).show()
    }

//    quando fecha a aplicacao

    override fun onDestroy() {
        super.onDestroy()
        Log.e(CICLO_VIDA, "App em onDestroy")
    }
}