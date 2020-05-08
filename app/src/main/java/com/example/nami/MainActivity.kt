package com.example.nami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.GridView
import android.widget.Toast
import com.example.nami.adapter.LanguageAdapters
import com.example.nami.model.LanguageItem


class MainActivity : AppCompatActivity(), OnItemClickListener{

    private var arrayList: ArrayList<LanguageItem>? = null
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

    private fun setDataList(): ArrayList<LanguageItem> {
        var arrayList: ArrayList<LanguageItem> = ArrayList()
        arrayList.add(LanguageItem(R.drawable.foto, "name 1"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 2"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 3"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 4"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 5"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 6"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 7"))
        arrayList.add(LanguageItem(R.drawable.foto, "name 8"))

        return arrayList
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var items:LanguageItem = arrayList!![position]
        Toast.makeText(applicationContext , items.name, Toast.LENGTH_LONG).show()
    }
}
