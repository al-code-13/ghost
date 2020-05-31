package com.example.nami.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.R
import com.example.nami.models.detailModels.ListElement
import kotlinx.android.synthetic.main.article_data_detail.view.*

class ItemsDetailAdapter(
    private val mContext: Context,
    private val data: List<ListElement>

) : RecyclerView.Adapter<ItemsDetailAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.name)
        var idProduct:TextView = v.findViewById(R.id.idProduct)
        var price:TextView = v.findViewById(R.id.price)
        var cant:TextView = v.findViewById(R.id.cant)
        var minusButton:TextView = v.findViewById(R.id.minusButton)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val v: View =
            LayoutInflater.from(mContext).inflate(R.layout.article_data_detail, parent, false)
        return ViewHolder(v)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(
        v: ViewHolder,
        position: Int
    ) {
        val elements = data[position]
        v.name.text = elements.article.name
        v.idProduct.text = "${elements.article.id}"
        v.price.text = "$ ${elements.article.value}"
        v.cant.text = "${elements.quantityArticle}"
        v.minusButton.setOnClickListener {
            elements.quantityArticle = (elements.quantityArticle.toInt() - 1).toString()

        }
    }
}