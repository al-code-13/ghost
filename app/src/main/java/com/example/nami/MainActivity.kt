package com.example.nami

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import androidx.appcompat.app.AlertDialog
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
        arrayList.add(ModelOrders("Juenito Perez", 12310, "30", "2020/15/16", "300004489", 200016))
        arrayList.add(ModelOrders("Pepito XS", 11380, "50", "2020/19/24", "300204487", 40000))
        arrayList.add(ModelOrders("No se que nombre", 13370, "30", "2020/14/14", "302004485", 2216))
        arrayList.add(ModelOrders("Dali Gaes", 14360, "10", "2020/13/34", "303004488", 16000))
        arrayList.add(ModelOrders("Nombre Lored", 15350, "20", "2020/12/14", "300204481", 20136))
        arrayList.add(ModelOrders("naolia", 16340, "60", "2020/11/20", "313004483", 203316))
        arrayList.add(ModelOrders("Naomy", 17330, "100", "2020/10/08", "313004482", 78101))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))
        arrayList.add(ModelOrders("Flen Caroline", 18320, "2", "2020/01/01", "300035481", 79516))

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val alert=AlertDialog.Builder(this)
        var items: ModelOrders = arrayList!![position]
        alert.setTitle("Wenas ${items.name}")
        alert.setMessage("Mensaje")
        alert.setPositiveButton("Si") { _:DialogInterface, i:Int->alert.setCancelable(true)}
        alert.setNegativeButton("No") { _:DialogInterface, i:Int->alert.setCancelable(true)}
        alert.show()
    }
}
