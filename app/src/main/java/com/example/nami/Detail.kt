package com.example.nami

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nami.model.ProductData
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.article_data_detail.view.*

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent: Intent = intent
        val orderId = intent.getStringExtra("orderId")
        val orderState = intent.getStringExtra("state")
        print(orderId)
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


        //articleList.adapter = adapter
        if (orderState == "Revisada") {
            for (i in products) {
                val v: View = layoutInflater.inflate(R.layout.article_data_detail, null)
                v.posicion.text = "${products.indexOf(i)}"
                v.name.text = i.name
                v.idProduct.text = "${i.id}"
                v.price.text = "$ ${i.price}"
                v.cant.text = "${i.cant}"

                layoutArticles.addView(v)
            }
        } else {

            for (i in products) {
                val v: View = layoutInflater.inflate(R.layout.article_data_detail_preview, null)
                v.name.text = i.name
                v.idProduct.text = "${i.id}"
                v.price.text = "$ ${i.price}"
                v.cant.text = "${i.cant}"

                layoutArticles.addView(v)
            }
        }
        when(orderState){
            "Pendiente"->{
                val v:View=layoutInflater.inflate(R.layout.buttons_article_pending,null)
                contentDetailPage.addView(v)
            }

            "Revisada" -> {
                val v:View=layoutInflater.inflate(R.layout.buttons_article_revised,null)
                contentDetailPage.addView(v)
            }
            "Ridder" -> {
                val v:View=layoutInflater.inflate(R.layout.buttons_article_ridder,null)
                contentDetailPage.addView(v)
            }
            else->{
                val v:View=layoutInflater.inflate(R.layout.buttons_article_delivered,null)
                contentDetailPage.addView(v)
            }
        }
    }
}