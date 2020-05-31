package com.example.nami

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nami.models.detailModels.DetailResponse
import com.example.nami.presenters.DetailPresenter
import com.example.nami.presenters.DetailUI
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.article_data_detail.view.*

class Detail : AppCompatActivity(), DetailUI {

    private var presenter : DetailPresenter?=null
    var userInfo = arrayOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intent: Intent = intent
        val orderId = intent.getIntExtra("orderId", -1)
        val function
        userInfo = intent.getStringArrayExtra("userInfo")
        Log.i("Id de la orden", userInfo.toString())
        presenter = DetailPresenter(orderId,this)
        name.text = userInfo[1] + " " + userInfo[2]
        idProduct.text = userInfo[0]
        phoneNumber.text = userInfo[5]
        method.text = userInfo[12]
        adress.text = userInfo[3]
        date.text = userInfo[6]
        time.text = userInfo[9]
        type.text = "Domicilio"
        //pay.text
        //change
        presenter!!.actionDetail()
    }

    override fun showDetailInfo(data: DetailResponse) {

        runOnUiThread {
            if (orderState == "28") {
                val articles = data.order.detailOrder.list
                for (i in articles) {
                    val v: View = layoutInflater.inflate(R.layout.article_data_detail, null)
                    v.name.text = i.article.name
                    v.idProduct.text = "${i.article.id}"
                    v.price.text = "$ ${i.valueTotalArticle}"
                    v.cant.text = "${i.quantityArticle}"
                    v.minusButton.setOnClickListener {
                        i.quantityArticle = (i.quantityArticle.toInt() - 1).toString()
                    }

                    v.moreButton.setOnClickListener {
                        i.quantityArticle = (i.quantityArticle.toInt() + 1).toString()
                    }

                    layoutArticles.addView(v)
                }
            } else {

                val articles = data.order.detailOrder.list
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
                "5" -> {
                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val layoutTakeButton: View = layoutInflater.inflate(R.layout.take_button, null)
                    val takebutton = layoutTakeButton.findViewById<Button>(R.id.takeButton)
                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    takebutton.setOnClickListener {
                        presenter.actionTake(orderId.toLong(), "2020-05-18")
                    }
                    closeButton.setOnClickListener {

                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                    buttonsLinearLayout.addView(layoutTakeButton)
                }
                "9" -> {
                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val layoutTakeButton: View = layoutInflater.inflate(R.layout.take_button, null)
                    val takebutton = layoutTakeButton.findViewById<Button>(R.id.takeButton)
                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    takebutton.setOnClickListener {
                        presenter.actionTake(orderId.toLong(), "2020-05-18")
                    }
                    closeButton.setOnClickListener {

                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                    buttonsLinearLayout.addView(layoutTakeButton)
                }
                "10" -> {

                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val layoutDeliverButton: View =
                        layoutInflater.inflate(R.layout.deliver_button, null)
                    val layoutFreeButton: View = layoutInflater.inflate(R.layout.free_button, null)
                    val layoutFreezeButton: View =
                        layoutInflater.inflate(R.layout.freeze_button, null)

                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    closeButton.setOnClickListener {

                    }
                    val deliverButton = layoutDeliverButton.findViewById<Button>(R.id.deliverButton)
                    deliverButton.setOnClickListener {
                        presenter.actionPutDeliverCourier(orderId.toLong())
                    }
                    val freeButton = layoutFreeButton.findViewById<Button>(R.id.freeButton)
                    freeButton.setOnClickListener {
                        presenter.actionRelease(orderId.toLong(), "observaciones")
                    }
                    val freezeButton = layoutFreezeButton.findViewById<Button>(R.id.freezeButton)
                    freezeButton.setOnClickListener {
                        presenter.actionPutFreeze(orderId.toLong(), 2)
                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                    buttonsLinearLayout.addView(layoutDeliverButton)
                    buttonsLinearLayout.addView(layoutFreeButton)
                    buttonsLinearLayout.addView(layoutFreezeButton)
                }
                "28" -> {
                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val layoutSaveButton: View = layoutInflater.inflate(R.layout.save_button, null)
                    val layoutFreeButton: View = layoutInflater.inflate(R.layout.free_button, null)
                    val layoutFreezeButton: View =
                        layoutInflater.inflate(R.layout.freeze_button, null)

                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    closeButton.setOnClickListener {

                    }
                    val deliverButton = layoutSaveButton.findViewById<Button>(R.id.pickButton)
                    deliverButton.setOnClickListener {
                        presenter.actionPutDeliverCustomer(orderId.toLong())
                    }
                    val freeButton = layoutFreeButton.findViewById<Button>(R.id.freeButton)
                    freeButton.setOnClickListener {
                        presenter.actionRelease(orderId.toLong(), "observaciones")
                    }
                    val freezeButton = layoutFreezeButton.findViewById<Button>(R.id.freezeButton)
                    freezeButton.setOnClickListener {
                        presenter.actionPutFreeze(orderId.toLong(), 2)
                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                    buttonsLinearLayout.addView(layoutSaveButton)
                    buttonsLinearLayout.addView(layoutFreeButton)
                    buttonsLinearLayout.addView(layoutFreezeButton)
                }
                "29" -> {
                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val layoutDeliverButton: View =
                        layoutInflater.inflate(R.layout.deliver_button, null)
                    val layoutFreeButton: View = layoutInflater.inflate(R.layout.free_button, null)
                    val layoutFreezeButton: View =
                        layoutInflater.inflate(R.layout.freeze_button, null)

                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    closeButton.setOnClickListener {

                    }
                    val deliverButton = layoutDeliverButton.findViewById<Button>(R.id.deliverButton)
                    deliverButton.setOnClickListener {
                        presenter.actionPutDeliverCustomer(orderId.toLong())
                    }
                    val freeButton = layoutFreeButton.findViewById<Button>(R.id.freeButton)
                    freeButton.setOnClickListener {
                        presenter.actionRelease(orderId.toLong(), "observaciones")
                    }
                    val freezeButton = layoutFreezeButton.findViewById<Button>(R.id.freezeButton)
                    freezeButton.setOnClickListener {
                        presenter.actionPutFreeze(orderId.toLong(), 2)
                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                    buttonsLinearLayout.addView(layoutDeliverButton)
                    buttonsLinearLayout.addView(layoutFreeButton)
                    buttonsLinearLayout.addView(layoutFreezeButton)
                }
                else -> {
                    val layoutCloseButton: View =
                        layoutInflater.inflate(R.layout.close_button, null)
                    val closeButton = layoutCloseButton.findViewById<Button>(R.id.closeButton)
                    closeButton.setOnClickListener {

                    }
                    buttonsLinearLayout.addView(layoutCloseButton)
                }
            }
        }
    }


    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun orderModifiedSuccessfull(state: String) {
        val intent = Intent(this, Detail::class.java)
        intent.putExtra("orderId", orderId)
        intent.putExtra("state", state)
        intent.putExtra("userInfo", userInfo)
        startActivity(intent)
        finish()
    }
}