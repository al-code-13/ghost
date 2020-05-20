package com.example.nami.adapter

import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.nami.R
import com.example.nami.models.auth.sections.Legend

class IndicatorsAdapter(
    val context: Context,
    var arrayList: List<Legend>
) : BaseAdapter() {


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v: View = View.inflate(context, R.layout.item_indicators, null)
        var name: TextView = v.findViewById(R.id.item)
        name.text = arrayList[position].name
        name.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.blue_circle,0,0,0)
        return v
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
}