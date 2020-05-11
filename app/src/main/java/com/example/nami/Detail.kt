package com.example.nami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nami.adapter.ProductsArrayAdapter
import com.example.nami.model.ProductData
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val producto1 = ProductData(0, "Leche Entera", 3500, 2)
        val producto2 = ProductData(1, "Leche Deslactosada", 3500, 2)
        val producto3 = ProductData(2, "Leche De Almendras", 3500, 2)
        val producto4 = ProductData(3, "Leche Achocolatada", 3500, 2)


        val products = listOf(producto1, producto2, producto3, producto4)

        val adapter = ProductsArrayAdapter(this, products)

        articleList.adapter = adapter

    }
}