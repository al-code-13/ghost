package com.example.nami.adapter

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.R
import com.example.nami.models.detailModels.ListElement

class ItemsDetailAdapter(
    private val mContext: Context,
    private var data: List<ListElement>

) : RecyclerView.Adapter<ItemsDetailAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var name: TextView = v.findViewById(R.id.name)
        var idProduct:TextView = v.findViewById(R.id.idProduct)
        var price:TextView = v.findViewById(R.id.price)
        var cant:TextView = v.findViewById(R.id.cant)
        var minusButton:ImageView = v.findViewById(R.id.minusButton)
        var moreButton:ImageView=v.findViewById(R.id.moreButton)
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
        v.moreButton.setOnClickListener {
            elements.quantityArticle = (elements.quantityArticle.toInt() + 1).toString()

        }
    }
}