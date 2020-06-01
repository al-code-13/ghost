package com.example.nami

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.adapter.ItemsDetailAdapter
import com.example.nami.controllers.services.ServiceFactory
import com.example.nami.models.detailModels.DetailResponse
import com.example.nami.presenters.DetailPresenter
import com.example.nami.presenters.DetailUI
import kotlinx.android.synthetic.main.activity_detail.*

class Detail : AppCompatActivity(), DetailUI {

    private var presenter: DetailPresenter? = null
    var recyclerItemsDetail: RecyclerView? = null
    var userInfo = arrayOf<String>()
    var behavior = -1
    lateinit var data: DetailResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val intent: Intent = intent
        val orderId = intent.getIntExtra("orderId", -1)
        behavior = intent.getIntExtra("behavior", -1)
        userInfo = intent.getStringArrayExtra("userInfo")
        Log.i("Id de la orden", userInfo.toString())
        presenter = DetailPresenter(orderId, this)
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
        recyclerItemsDetail = findViewById(R.id.layoutArticles)
        presenter!!.actionDetail()
    }

    fun createArticleView() {
        recyclerItemsDetail!!.setHasFixedSize(true)
        recyclerItemsDetail!!.layoutManager = LinearLayoutManager(this)
        recyclerItemsDetail?.adapter =
            ItemsDetailAdapter(this, data.order.detailOrder.list, behavior)
    }

    override fun showDetailInfo(data: DetailResponse) {

        runOnUiThread {
            this.data = data
            createArticleView()
            createButtons(behavior)
        }
    }

    fun createButtons(newFunction: Int) {
        runOnUiThread {
            buttonsLinearLayout.removeAllViews()
            var actionsList =
                ServiceFactory.data.behaviors.firstOrNull { it.id == newFunction }?.actions
            for (i in actionsList!!) {
                val action = ServiceFactory.data.actions.firstOrNull { it.id == i }
                val layoutNewButton = layoutInflater.inflate(R.layout.save_button, null)
                val button = layoutNewButton.findViewById<Button>(R.id.pickButton)
                Log.i("accion", action!!.name)
                if (action.destructive) {
                    button.setBackgroundColor(Color.parseColor("#ff0000"))
                }
                val param: ViewGroup.MarginLayoutParams =
                    button.layoutParams as ViewGroup.MarginLayoutParams
                param.setMargins(10, 10, 10, 10)
                button.layoutParams = param
                button.text = "${action.name}"
                button.setOnClickListener {
                    when (action.id) {
                        1 -> {

                        }
                        2 -> {

                        }
                        3 -> {
                            presenter!!.actionTake()
                        }
                        4 -> {

                        }
                        5 -> {
                            presenter!!.actionRelease("mklklm")
                        }
                        6 -> {
                            presenter!!.actionPutDeliverCourier()
                        }
                        7 -> {
                            presenter!!.actionPutDeliverCustomer()
                        }
                        8 -> {
                            presenter!!.actionPutFreeze(2)
                        }
                    }
                }
                buttonsLinearLayout.addView(layoutNewButton)
            }
        }
    }

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

    override fun showDetailFunctionReleased() {
        runOnUiThread {
            createArticleView()
            createButtons(3)
        }
    }

    override fun showDetailFunctionTaked() {
        runOnUiThread {
            createArticleView()
            createButtons(2)
        }
    }

    override fun showDetailFunctioDeliverCourier() {
        runOnUiThread {
            createArticleView()
            createButtons(8)
        }
    }

    override fun showDetailFunctionDeliverCustomer() {
        runOnUiThread {
            createArticleView()
            createButtons(9)
        }
    }

    override fun showDetailFunctionFreeze() {
        runOnUiThread {
            createArticleView()
            createButtons(behavior)
        }
    }

}