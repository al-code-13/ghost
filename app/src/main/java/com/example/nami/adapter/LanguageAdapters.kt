package com.example.nami.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.Detail
import com.example.nami.R
import com.example.nami.model.ModelOrders

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

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v: View =
            LayoutInflater.from(mContext).inflate(R.layout.card_view_item_grid, parent, false)
        return ViewHolder(v).listen { pos, type ->
            val items = mDataSet[pos]
            val dialog = Dialog(mContext)
            val dialogView = LayoutInflater.from(mContext).inflate(R.layout.activity_popup,null)
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
                val intent: Intent = Intent(mContext, Detail::class.java)
                intent.putExtra("orderId", items.idOrder)
                intent.putExtra("state", items.state)
                startActivity(mContext,intent,null)
            }
            detail.text = "Detalles"
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.black))
            dialog.setContentView(dialogView)
            dialog.show()}
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
