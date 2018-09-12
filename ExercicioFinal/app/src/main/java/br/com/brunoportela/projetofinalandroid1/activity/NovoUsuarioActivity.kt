package br.com.brunoportela.projetofinalandroid1.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import br.com.brunoportela.projetofinalandroid1.R
import br.com.brunoportela.projetofinalandroid1.entity.Usuario
import br.com.brunoportela.projetofinalandroid1.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.android.synthetic.main.activity_novousuario.*
import org.jetbrains.anko.longToast

class NovoUsuarioActivity : AppCompatActivity(){

    var mAuth : FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_novousuario)

        mAuth = FirebaseAuth.getInstance()

        btnCriarConta.setOnClickListener {

            var util : Util = Util()

            if(edtEmail.text.toString().isEmpty() || edtSenha.text.toString().isEmpty() || edtConfirmarSenha.text.toString().isEmpty()){
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(!util.comparaSenhas(edtSenha.text.toString(), edtConfirmarSenha.text.toString())){
                Toast.makeText(this, "Senhas são diferentes", Toast.LENGTH_LONG).show()
                edtSenha.setText("")
                edtConfirmarSenha.setText("")
                return@setOnClickListener
            }

            if(!util.senhaValida(edtSenha.text.toString())){
                Toast.makeText(this, "A senha deve conter 6 dígitos, e conter pelo menos um caractere maiúsculo, um caractere especial e um número", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(!util.emailValido(edtEmail.text.toString())){
                Toast.makeText(this, "Email inválido", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            var u : Usuario = Usuario()
            u.email = edtEmail.text.toString()
            u.senha = edtSenha.text.toString()

            mAuth?.let { m ->
                m.createUserWithEmailAndPassword(u.email,u.senha).addOnCompleteListener({ task ->
                    if(task.isSuccessful){
                        Log.i("Novousuario", mAuth?.currentUser?.uid)
                        Log.i("Novousuario", mAuth?.currentUser?.isEmailVerified.toString())

                        if(mAuth?.currentUser != null && mAuth?.currentUser?.isEmailVerified == false){
                            mAuth?.currentUser?.sendEmailVerification()
                        }

                        startActivity(Intent(this@NovoUsuarioActivity, LoginActivity::class.java))
                        finish()
                    }else if(task.exception is FirebaseAuthInvalidCredentialsException){
                        longToast("E-mail inválido, digite um novo email")
                    }else if(task.exception is FirebaseAuthUserCollisionException){
                        longToast("E-mail já cadastrado")
                    }
                })
            }
        }

        tvLogin.setOnClickListener {
            startActivity(Intent(this@NovoUsuarioActivity, LoginActivity::class.java))
        }
    }

}