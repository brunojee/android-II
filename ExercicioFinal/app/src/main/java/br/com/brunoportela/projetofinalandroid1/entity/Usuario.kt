package br.com.brunoportela.projetofinalandroid1.entity

import android.arch.persistence.room.ColumnInfo

data class Usuario (

        @ColumnInfo(name = "nome") var nome: String = "",

        @ColumnInfo(name = "email") var email: String = "",

        @ColumnInfo(name = "matricula") var matricula: String = "",

        @ColumnInfo(name = "senha") var senha: String = "",

        @ColumnInfo(name = "telefone") var telefone: String = "",

        @ColumnInfo(name = "endereco") var endereco: String = "",

        @ColumnInfo(name = "foto") var foto: String = ""

)