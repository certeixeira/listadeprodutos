package com.example.listadeprodutos.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadeprodutos.ListaProdutosAdapter
import com.example.listadeprodutos.R
import java.math.BigDecimal

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val nome: TextView = findViewById<TextView>(R.id.nome)
//        nome.text = "Cesta de Frutas"
//        val descricao = findViewById<TextView>(R.id.descricao)
//        descricao.text = "Laranja, manga e maçã"
//        val valor = findViewById<TextView>(R.id.valor)
//        valor.text = "19.99"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(
                nome = "teste",
                descricao = "teste desc",
                valor = BigDecimal("19.99")
            ),
            Produto(
                nome = "teste1",
                descricao = "teste desc1",
                valor = BigDecimal("191.99")
            )
        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}