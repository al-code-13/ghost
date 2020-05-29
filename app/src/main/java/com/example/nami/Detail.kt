package com.example.nami

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nami.models.detailModels.DetailResponse
import com.example.nami.presenters.DetailPresenter
import com.example.nami.presenters.DetailUI
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.article_data_detail.view.*

class Detail : AppCompatActivity(), DetailUI {

    private val presenter = DetailPresenter(this)
    private var token = ""
    var orderId=""
    var orderState=""
    var actionList=""
    private val gson=GsonBuilder().create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent: Intent = intent
         orderId = intent.getStringExtra("orderId")
         orderState = intent.getStringExtra("state")
        val userInfo= intent.getStringArrayExtra("userInfo")
        Log.i("Id de la orden",userInfo.toString())
         //actionList = intent.getStringExtra("actionList")
        name.text=userInfo[1]+" "+userInfo[2]
        idProduct.text=userInfo[0]
        phoneNumber.text=userInfo[5]
        method.text= userInfo[12]
        adress.text=userInfo[3]
        date.text=userInfo[6]
        time.text = userInfo[9]
        type.text="Domicilio"
        //pay.text
        //change
        token = this.getSharedPreferences("localStorage", Context.MODE_PRIVATE).getString("token","localStorage").toString()
        presenter.actionDetail(
            token,
            orderId.toLong()
        )


    }

    override fun showDetailInfo(data: DetailResponse) {

        runOnUiThread {
            if (orderState == "28") {
                val articles=data.order.detailOrder.list
                for (i in articles) {
                    val v: View = layoutInflater.inflate(R.layout.article_data_detail, null)
                    v.name.text = i.article.name
                    v.idProduct.text = "${i.article.id}"
                    v.price.text = "$ ${i.valueTotalArticle}"
                    v.cant.text = "${i.quantityArticle}"

                    layoutArticles.addView(v)
                }
            } else {

                val articles=data.order.detailOrder.list
                for (i in articles) {
                    val v: View = layoutInflater.inflate(R.layout.article_data_detail_preview, null)
                    v.name.text = i.article.name
                    v.idProduct.text = "${i.article.id}"
                    v.price.text = "$ ${i.valueTotalArticle}"
                    v.cant.text = "${i.quantityArticle}"

                    layoutArticles.addView(v)
                }
            }
            when (orderState) {
                "Pendiente" -> {
                    val v: View = layoutInflater.inflate(R.layout.buttons_article_pending, null)
                    contentDetailPage.addView(v)
                }

                "Revisada" -> {
                    val v: View = layoutInflater.inflate(R.layout.buttons_article_revised, null)
                    contentDetailPage.addView(v)
                }
                "Ridder" -> {
                    val v: View = layoutInflater.inflate(R.layout.buttons_article_ridder, null)
                    contentDetailPage.addView(v)
                }
                else -> {
                    val v: View = layoutInflater.inflate(R.layout.buttons_article_delivered, null)
                    contentDetailPage.addView(v)
                }
            }
        }
    }


    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }
}