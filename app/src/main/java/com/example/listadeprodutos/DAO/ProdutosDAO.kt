package com.example.listadeprodutos.DAO

import com.example.listadeprodutos.activity.Produto

class ProdutosDAO {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos() : List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}