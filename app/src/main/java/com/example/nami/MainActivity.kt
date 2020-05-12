package com.example.nami

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.nami.adapter.LanguageAdapters
import com.example.nami.model.ModelOrders


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var arrayList: ArrayList<ModelOrders>? = null
    private var gridView: GridView? = null
    private var languageAdapters: LanguageAdapters? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gridView = findViewById(R.id.my_grid_view_list)
        arrayList = ArrayList()
        arrayList = setDataList()
        languageAdapters = LanguageAdapters(applicationContext, arrayList!!)
        gridView?.adapter = languageAdapters
        gridView?.onItemClickListener = this

    }

    private fun setDataList(): ArrayList<ModelOrders> {
        var arrayList: ArrayList<ModelOrders> = ArrayList()
        arrayList.add(
            ModelOrders(
                "Juenito Perez",
                12310,
                "30",
                "2020/15/16",
                "300004489",
                200016,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Pepito XS",
                11380,
                "50",
                "2020/19/24",
                "300204487",
                40000,
                "Revisada"
            )
        )
        arrayList.add(
            ModelOrders(
                "No se que nombre",
                13370,
                "30",
                "2020/14/14",
                "302004485",
                2216,
                "Ridder"
            )
        )
        arrayList.add(
            ModelOrders(
                "Dali Gaes",
                14360,
                "10",
                "2020/13/34",
                "303004488",
                16000,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Nombre Lored",
                15350,
                "20",
                "2020/12/14",
                "300204481",
                20136,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "naolia",
                16340,
                "60",
                "2020/11/20",
                "313004483",
                203316,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Naomy",
                17330,
                "100",
                "2020/10/08",
                "313004482",
                78101,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )
        arrayList.add(
            ModelOrders(
                "Flen Caroline",
                18320,
                "2",
                "2020/01/01",
                "300035481",
                79516,
                "Pendiente"
            )
        )

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items: ModelOrders = arrayList!![position]

        val dialog = AlertDialog.Builder(this)
        val dialogView=layoutInflater.inflate(R.layout.activity_popup,null)
        val title=dialogView.findViewById<TextView>(R.id.titleOrderId)
        val option=dialogView.findViewById<TextView>(R.id.optionone)
        val detail=dialogView.findViewById<TextView>(R.id.detail)
        title.text = "Orden #${items.idOrder}"
        when (items.state) {
            "Pendiente" -> {
                option.text = "Tomar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car,0     ,0   ,0)
            }
            "Revisada" -> {
                option.text = "Comprar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bag,0     ,0   ,0)
            }
            "Ridder" -> {
                option.text = "Entregar"
                option.setCompoundDrawablesWithIntrinsicBounds(R.drawable.persons,0     ,0   ,0)
            }
        }
        option.setOnClickListener {
            val intent: Intent = Intent(this, Detail::class.java)
            intent.putExtra("state", items.state)
            startActivity(intent)
        }
        detail.text="Detalles"
        dialog.setView(dialogView)
        dialog.show()
    }
}
