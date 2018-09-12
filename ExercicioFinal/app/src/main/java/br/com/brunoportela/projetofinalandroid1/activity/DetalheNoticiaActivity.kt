package br.com.brunoportela.projetofinalandroid1.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.brunoportela.projetofinalandroid1.R
import kotlinx.android.synthetic.main.activity_detalhe_noticia.*

class DetalheNoticiaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_noticia)

        imgFechar.setOnClickListener {
            finish()
        }

    }
}