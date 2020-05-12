package com.example.nami.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.nami.R
import com.example.nami.model.ModelOrders
import com.example.nami.model.ProductData
import kotlinx.android.synthetic.main.article_data_detail.view.*

class LanguageAdapters(var context: Context,var arrayList: ArrayList<ModelOrders>):BaseAdapter() {


    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.card_view_item_grid ,null)
        var names : TextView = view.findViewById(R.id.name)
        var idOrder : TextView = view.findViewById(R.id.idOrder)
        var amount : TextView = view.findViewById(R.id.amount)
        var date : TextView = view.findViewById(R.id.date)
        var cell : TextView = view.findViewById(R.id.phone)
        var total : TextView = view.findViewById(R.id.total)

        var listItem: ModelOrders = arrayList[position]
        names.text = listItem.name
        idOrder.text = listItem.idOrder.toString()
        amount.text = listItem.amount
        date.text = listItem.date
        cell.text = listItem.cell
        total.text = listItem.total.toString()
        return view
    }
}

class ProductsArrayAdapter (private val  mContext: Context, private val listaProducts :List<ProductData>):
    ArrayAdapter<ProductData>(mContext,0,listaProducts){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout= LayoutInflater.from(mContext).inflate(R.layout.article_data_detail,parent,false)
        val product=listaProducts[position]
        layout.posicion.text="$position"
        layout.name.text=product.name
        layout.idProduct.text="${product.id}"
        layout.price.text="$ ${product.price}"
        layout.cant.text="${product.cant}"

        return layout
    }

}