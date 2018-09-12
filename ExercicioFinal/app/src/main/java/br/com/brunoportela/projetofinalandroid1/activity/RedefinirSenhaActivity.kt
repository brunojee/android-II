package br.com.brunoportela.projetofinalandroid1.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.brunoportela.projetofinalandroid1.R
import br.com.brunoportela.projetofinalandroid1.entity.Usuario
import br.com.brunoportela.projetofinalandroid1.util.Util
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.android.synthetic.main.activity_redefinirsenha.*
import org.jetbrains.anko.longToast

class RedefinirSenhaActivity : AppCompatActivity(){

    var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_redefinirsenha)

        mAuth = FirebaseAuth.getInstance()

        btnRedefinir.setOnClickListener {

            var u : Usuario = Usuario()
            u.email = edtEmail.text.toString()

            var util : Util = Util()
            if(!util.emailValido(u.email)){
                Log.i("Redefinirsenha:", "Email inválido")
                return@setOnClickListener
            }

            mAuth?.let { m ->
                m.sendPasswordResetEmail(u.email).addOnCompleteListener({ task ->
                    if(task.isSuccessful){
                        longToast("Para redefinir a senha verifique seu e-mail")
                    }else if(task.exception is FirebaseAuthInvalidUserException){
                        longToast("Não existe usuário cadastrado com esse e-mail")
                        Log.i("Redefinirsenha", task.exception.toString())
                    }
                })
            }

        }
    }
}