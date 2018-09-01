package br.com.brunoportela.projetofinalandroid1.atividades

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.brunoportela.projetofinalandroid1.R
import br.com.brunoportela.projetofinalandroid1.database.ConexaoBanco
import br.com.brunoportela.projetofinalandroid1.service.UsuarioService
import br.com.brunoportela.projetofinalandroid1.util.Utilitarios
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_perfil.*

class PerfilActivity : AppCompatActivity() {

    var mOAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        mOAuth = FirebaseAuth.getInstance()

        // ####### Validando usuário Logado ##################
        var usuario = mOAuth?.currentUser
        if(usuario == null){
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }
        // ###################################################


       // val usuario = UsuarioService().obterUsuarioPorId(idUsuario!!, applicationContext)

        email.setText(usuario?.email)
        nome.setText(usuario?.displayName)
        //matricula.setText(usuario.)
        telefone.setText(usuario?.phoneNumber)





        botaoSalvarPerfil.setOnClickListener{


            val campos = HashMap<String, String>()
            campos.put("nome",             nome.text.toString())
            campos.put("senha",             senha.text.toString())
            campos.put("senhaConfirmacao",  senhaConfirmacao.text.toString())
            campos.put("matricula",         matricula.text.toString())
            campos.put("telefone",          telefone.text.toString())

            try {
                validarCampos(campos)


//                mOAuth?.let { auth ->
//
//                    auth.createUserWithEmailAndPassword(campos.get("email") as String, campos.get("senha") as String).addOnCompleteListener({task ->
//
//                        if(task.isSuccessful){
//                            val intent = Intent(this, LoginActivity::class.java )
//                            startActivity(intent)
//                        } else {
//                            Utilitarios.mostrarMensagem(this, task.exception.toString())
//                        }
//
//
//                    })
//                }

                /**
                val usuarioBanco = UsuarioService().obterUsuarioPorId(idUsuario, applicationContext)

                usuarioBanco.nome       = campos.get("nome") as String
                usuarioBanco.matricula  = campos.get("matricula") as String
                usuarioBanco.telefone   = campos.get("telefone") as String

                if((campos.get("senha") as String).length > 0){
                    usuarioBanco.senha   = campos.get("senha") as String
                }

                UsuarioService().atualizarUsuario(usuarioBanco, applicationContext)
                */


                Utilitarios.mostrarMensagem(this, "Dados salvos com sucesso")

                val intent = Utilitarios.novaIntent(this, PrincipalActivity::class.java)
                startActivity(intent)

                this.finish()



            }catch (ex : Exception){
                Utilitarios.mostrarMensagem(this, ex.message!!)
            }

        }
    }


    fun validarCampos(campos : HashMap<String, String>) {


        if (campos == null) {
            throw Exception("Parâmetro inválido")
        } else {
            val senha            = campos.get("senha") as String;
            val senhaConfirmacao = campos.get("senhaConfirmacao")as String;

            if (senha != null && senha.length > 0) {


                // Validar email já existente


                if (senha.length < 6) {
                    throw Exception("A senha deverá conter no mínimo 6 caracteres")
                }

                // Validar se existe uma letra maiúscula

                // Validar caractere especial

                // Validar numero


                if (!senha.equals(senhaConfirmacao)) {
                    throw Exception("As senhas estão diferentes")
                }
            }

        }
    }
}
