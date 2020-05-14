package com.example.nami

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.nami.adapter.DemoAdapter
import com.example.nami.adapter.LanguageAdapters
import com.example.nami.adapter.MyAdapter
import com.example.nami.model.ModelOrders
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var arrayList: ArrayList<ModelOrders>? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Pedientes"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Revisados"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Ridder"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Entregados"))
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = adapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items: ModelOrders = arrayList!![position]

        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.activity_popup, null)
        val title = dialogView.findViewById<TextView>(R.id.titleOrderId)
        val option = dialogView.findViewById<TextView>(R.id.optionone)
        val detail = dialogView.findViewById<TextView>(R.id.detail)
        title.text = "Orden #${items.idOrder}"
        when (items.state) {
            "Pendiente" -> {
                option.text = "Tomar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car, 0, 0, 0)
            }
            "Revisada" -> {
                option.text = "Comprar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bag, 0, 0, 0)
            }
            "Ridder" -> {
                option.text = "Entregar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.persons, 0, 0, 0)
            }
        }
        option.setOnClickListener {
            val intent: Intent = Intent(this, Detail::class.java)
            intent.putExtra("orderId", items.idOrder)
            intent.putExtra("state", items.state)
            startActivity(intent)
        }
        detail.text = "Detalles"
        dialog.setView(dialogView)
        dialog.show()
    }
}
