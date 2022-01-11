package com.example.listadeprodutos.extensions

import android.widget.ImageView
import coil.load
import com.example.listadeprodutos.R

fun ImageView.tentaCarregarImagem(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)

    }
}