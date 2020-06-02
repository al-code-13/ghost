package com.example.nami.adapter

import OrdersList
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nami.Detail
import com.example.nami.R
import com.example.nami.controllers.services.ServiceFactory
import kotlinx.android.synthetic.main.action_item.view.*

class OrdersAdapter(
    private val mContext: Context,
    private val mDataSet: List<OrdersList>
) :
    RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var card: CardView = v.findViewById(R.id.card)
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

        return ViewHolder(v).listen { pos, _ ->
            val items = mDataSet[pos]
            val legend = ServiceFactory.data.behaviors.firstOrNull { it.id == items.behavior }
            if (legend != null) {
                if (legend.action != null) {
                    verDetalle(items)
                } else {
                    val dialog = Dialog(mContext)
                    val dialogView =
                        LayoutInflater.from(mContext).inflate(R.layout.activity_popup, null)
                    val title = dialogView.findViewById<TextView>(R.id.titleOrderId)
                    title.text = "Orden #${items.id}"
                    val layoutActions = dialogView.findViewById<LinearLayout>(R.id.listActions)
                    for (id in legend.actions!!) {
                        val v: View =
                            LayoutInflater.from(mContext).inflate(R.layout.action_item, null)
                        v.setOnClickListener {
                            if (id == 2) {//Ver detalle
                                verDetalle(items)
                            }
                        }
                        v.action.text =
                            ServiceFactory.data.actions.firstOrNull { it.id == id }?.name
                        layoutActions.addView(v)
                    }

                    dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.black))
                    dialog.setContentView(dialogView)
                    dialog.show()
                }
            }
        }
    }

    fun verDetalle(items: OrdersList) {
        var datos: Array<String> = arrayOf(
            items.id.toString(),
            items.name,
            items.lastname,
            items.address,
            items.value,
            items.phoneClient,
            items.date,
            items.origin,
            items.idCodBranch.toString(),
            items.hour,
            items.idState.toString(),
            items.observations,
            items.methodPay.name,
            items.pickingOrder.toString(),
            items.detailOrder.totalItems.toString(),
            items.behavior.toString()
        )
        val intent: Intent = Intent(mContext, Detail::class.java)
        intent.putExtra("orderId", items.id)
        intent.putExtra("userInfo", datos)
        intent.putExtra("behavior", items.behavior)
        startActivity(mContext, intent, null)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        holder.card.setCardBackgroundColor(Color.parseColor(ServiceFactory.data.behaviors.firstOrNull{it.id==mDataSet[position].behavior}!!.color))

        holder.names.text = mDataSet[position].name

        holder.idOrder.text = mDataSet[position].id.toString()

        holder.amount.text = mDataSet[position].detailOrder.totalItems.toString()

        holder.date.text = mDataSet[position].date

        holder.cell.text = mDataSet[position].phoneClient

        holder.total.text = mDataSet[position].value

    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }
}
