package com.example.nami

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.nami.adapter.DemoAdapter
import com.example.nami.model.ModelOrders

class HomeFragment(private val mContext:Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val getData: Array<ModelOrders> =
            arrayOf(
                ModelOrders(
                    "Juenito Perez",
                    12310,
                    "30",
                    "2020/15/16",
                    "300004489",
                    200016,
                    "Pendiente"
                ),
                ModelOrders(
                    "Pepito XS",
                    11380,
                    "50",
                    "2020/19/24",
                    "300204487",
                    40000,
                    "Revisada"
                ),
                ModelOrders(
                    "No se que nombre",
                    13370,
                    "30",
                    "2020/14/14",
                    "302004485",
                    2216,
                    "Ridder"
                ),
                ModelOrders(
                    "Dali Gaes",
                    14360,
                    "10",
                    "2020/13/34",
                    "303004488",
                    16000,
                    "Finalizada"
                ),
                ModelOrders(
                    "Nombre Lored",
                    15350,
                    "20",
                    "2020/12/14",
                    "300204481",
                    20136,
                    "Pendiente"
                ),
                ModelOrders(
                    "naolia",
                    16340,
                    "60",
                    "2020/11/20",
                    "313004483",
                    203316,
                    "Pendiente"
                ),
                ModelOrders(
                    "Naomy",
                    17330,
                    "100",
                    "2020/10/08",
                    "313004482",
                    78101,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                ),
                ModelOrders(
                    "Flen Caroline",
                    18320,
                    "2",
                    "2020/01/01",
                    "300035481",
                    79516,
                    "Pendiente"
                )
            )
        val v:View=inflater!!.inflate(R.layout.fragment_home, container, false)
        val reciclerView=v.findViewById<AutofitRecyclerView>(R.id.my_grid_view_list)
        reciclerView.setHasFixedSize(true)
        reciclerView.adapter = DemoAdapter(mContext, getData)
        return v
    }
}