package com.example.nami

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nami.adapter.ProductsArrayAdapter
import com.example.nami.model.ProductData
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        val orderId = intent.getStringExtra("orderId")
        val orderState = intent.getStringExtra("state")
        idProduct.text = orderId


        val products = listOf(
            ProductData(0, "Leche Entera", 3500, 2),
            ProductData(1, "Leche Deslactosada", 3500, 2),
            ProductData(2, "Leche De Almendras", 3500, 2),
            ProductData(3, "Leche Achocolatada", 3500, 2),

            ProductData(0, "Leche Entera", 3500, 2),
            ProductData(1, "Leche Deslactosada", 3500, 2),
            ProductData(2, "Leche De Almendras", 3500, 2),
            ProductData(3, "Leche Achocolatada", 3500, 2),

            ProductData(0, "Leche Entera", 3500, 2),
            ProductData(1, "Leche Deslactosada", 3500, 2),
            ProductData(2, "Leche De Almendras", 3500, 2),
            ProductData(3, "Leche Achocolatada", 3500, 2),

            ProductData(0, "Leche Entera", 3500, 2),
            ProductData(1, "Leche Deslactosada", 3500, 2),
            ProductData(2, "Leche De Almendras", 3500, 2),
            ProductData(3, "Leche Achocolatada", 3500, 2)

            )

        val adapter = ProductsArrayAdapter(this, products)

        articleList.adapter = adapter

    }
}