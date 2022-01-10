package com.example.listadeprodutos.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import coil.load
import com.example.listadeprodutos.DAO.ProdutosDAO
import com.example.listadeprodutos.R
import com.example.listadeprodutos.databinding.ActivityFormularioProdutoBinding
import com.example.listadeprodutos.databinding.FormularioImagemBinding
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity(R.layout.activity_formulario_produto) {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            val bindingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioImagem.formularioImagemBtnCarregar.setOnClickListener {
                val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                bindingFormularioImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bindingFormularioImagem.formularioImagemUrl.text.toString()
                    binding.activityFormularioProdutoImagem.load(url)
                }
                .setNegativeButton("Cancelar") { _, _ ->

                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDAO()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            Log.i("FormularioProduto", "onCreate: $produtoNovo")
            dao.adiciona(produtoNovo)
            Log.i("FormularioProduto", "onCreate: ${dao.buscaTodos()}")
            finish()
        }
        setContentView(binding.root)
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorEmtexto = campoValor.text.toString()
        val valor = if (valorEmtexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmtexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )

    }
}
