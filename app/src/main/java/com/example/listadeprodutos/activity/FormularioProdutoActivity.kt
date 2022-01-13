package com.example.listadeprodutos.activity

import android.os.Build.VERSION.SDK_INT
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.load
import com.example.listadeprodutos.DAO.ProdutosDAO
import com.example.listadeprodutos.R
import com.example.listadeprodutos.databinding.ActivityFormularioProdutoBinding
import com.example.listadeprodutos.databinding.FormularioImagemBinding
import com.example.listadeprodutos.dialog.FormularioImagemDialog
import com.example.listadeprodutos.extensions.tentaCarregarImagem
import java.math.BigDecimal

class FormularioProdutoActivity :
    AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    private var url:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        title = "Cadastrar produto"
        configuraBotaoSalvar()

        val imageLoader = ImageLoader.Builder(this)
            .componentRegistry {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder(this@FormularioProdutoActivity))
                } else {
                    add(GifDecoder())
                }
            }
            .build()

        binding.activityFormularioProdutoImagem.setOnClickListener {
            FormularioImagemDialog(this)
                .mostra(url) { imagem ->
                    url = imagem
                    binding.activityFormularioProdutoImagem.tentaCarregarImagem(url)

                }
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
