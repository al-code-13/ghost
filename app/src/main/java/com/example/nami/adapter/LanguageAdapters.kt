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
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.nami.Detail
import com.example.nami.R
import com.example.nami.models.sections.Action
import com.example.nami.models.sections.Legend
import kotlinx.android.synthetic.main.action_item.view.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.article_data_detail.view.*

class DemoAdapter(
    private val mContext: Context,
    private val titleOrder: String,
    private val mDataSet: List<OrdersList>,
    private val legendList: List<Legend>,
    private val actionList:List<Action>
) :
    RecyclerView.Adapter<DemoAdapter.ViewHolder>() {


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

            val serviceActions:List<Long>?=legendList[mDataSet[pos].function.toInt()-1].actions
            val items = mDataSet[pos]
            val dialog = Dialog(mContext)
            val dialogView = LayoutInflater.from(mContext).inflate(R.layout.activity_popup, null)
            val title = dialogView.findViewById<TextView>(R.id.titleOrderId)
            //title.text = "Orden #${items.pickingOrder.list[0].id}"
            val layoutActions=dialogView.findViewById<LinearLayout>(R.id.listActions)
            Log.i("lista de acciones", serviceActions.toString())
            for (i in serviceActions!!) {
                    val v: View = LayoutInflater.from(mContext).inflate(R.layout.action_item, null)
                    Log.i("accion",actionList[i.toInt()-1].name)
                    v.action.text=actionList[i.toInt()-1].name
                    layoutActions.addView(v)
                }

            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.black))
            dialog.setContentView(dialogView)
            dialog.show()
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        Log.i("lista de acciones ",actionList.toString())
        //Log.i("Lista ",mDataSet[position].pickingOrder.list[0].totalPicker.toString() )

        holder.card.setCardBackgroundColor(Color.parseColor(legendList[mDataSet[position].function.toInt()].color))

        holder.names.text = mDataSet[position].name

        holder.idOrder.text = mDataSet[position].id.toString()

        holder.amount.text = mDataSet[position].detailOrder.totalItems.toString()

        holder.date.text = mDataSet[position].date

        holder.cell.text = mDataSet[position].phoneClient

        //if (mDataSet[position].pickingOrder.list[0] != null) {
        //    holder.total.text = mDataSet[position].pickingOrder.list[0].totalPicker
        //} else {
        //    holder.total.visibility = View.GONE
        //}

    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }
}
