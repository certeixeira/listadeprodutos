package com.example.listadeprodutos.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.inflate
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listadeprodutos.DAO.ProdutosDAO
import com.example.listadeprodutos.ListaProdutosAdapter
import com.example.listadeprodutos.R
import com.example.listadeprodutos.databinding.ActivityFormularioProdutoBinding.inflate
import com.example.listadeprodutos.databinding.ActivityListaProdutosBinding
import com.example.listadeprodutos.databinding.ActivityListaProdutosBinding.inflate
import com.example.listadeprodutos.databinding.ProdutoItemBinding.inflate
import com.example.listadeprodutos.dialog.FormularioImagemDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaProdutosActivity: AppCompatActivity() {

    private val dao = ProdutosDAO()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())
    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraRecyclerView()
        configuraFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
        configuraFab()
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutosFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(this, FormularioProdutoActivity::class.java)
        startActivity(intent)
        setContentView(binding.root)
    }

    private fun configuraRecyclerView() {
        val recyclerView = binding.activityListaProdutosRecyclerView
        val dao = ProdutosDAO()
        Log.i("MainActivity", "onCreate: ${dao.buscaTodos()}")
        recyclerView.adapter = adapter
    }
}
