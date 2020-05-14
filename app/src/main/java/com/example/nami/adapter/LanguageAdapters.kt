package com.example.nami.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.R
import com.example.nami.model.ModelOrders


class LanguageAdapters(var context: Context, var arrayList: ArrayList<ModelOrders>) :
    BaseAdapter() {


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
        var v: View = View.inflate(context, R.layout.card_view_item_grid, null)
        var names: TextView = v.findViewById(R.id.name)
        var idOrder: TextView = v.findViewById(R.id.idOrder)
        var amount: TextView = v.findViewById(R.id.amount)
        var date: TextView = v.findViewById(R.id.date)
        var cell: TextView = v.findViewById(R.id.phone)
        var total: TextView = v.findViewById(R.id.total)

        var listItem: ModelOrders = arrayList[position]
        names.text = listItem.name
        idOrder.text = listItem.idOrder.toString()
        amount.text = listItem.amount
        date.text = listItem.date
        cell.text = listItem.cell
        total.text = listItem.total.toString()
        return v
    }
}

class DemoAdapter(
    private val mContext: Context,
    private val mDataSet: Array<ModelOrders>
) :
    RecyclerView.Adapter<DemoAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var names: TextView = v.findViewById(R.id.name)
        var idOrder: TextView = v.findViewById(R.id.idOrder)
        var amount: TextView = v.findViewById(R.id.amount)
        var date: TextView = v.findViewById(R.id.date)
        var cell: TextView = v.findViewById(R.id.phone)
        var total: TextView = v.findViewById(R.id.total)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(mContext).inflate(R.layout.card_view_item_grid, parent, false)
        //v.layoutParams.height=(250)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.names.text = mDataSet[position].name

        holder.idOrder.text = mDataSet[position].idOrder.toString()

        holder.amount.text = mDataSet[position].amount.toString()

        holder.date.text = mDataSet[position].date.toString()

        holder.cell.text = mDataSet[position].cell.toString()

        holder.total.text = mDataSet[position].total.toString()

    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }
}
