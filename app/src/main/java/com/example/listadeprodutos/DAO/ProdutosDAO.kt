package com.example.listadeprodutos.DAO

import com.example.listadeprodutos.activity.Produto
import java.math.BigDecimal

class ProdutosDAO {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de frutas",
                descricao = "Laranja, Maçã e Uva",
                valor = BigDecimal("19.83")
            )
        )
    }
}