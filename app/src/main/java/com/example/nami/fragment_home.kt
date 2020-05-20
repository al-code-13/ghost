package com.example.nami

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.nami.adapter.DemoAdapter
import com.example.nami.model.ModelOrders

class HomeFragment(private val mContext: Context, private val visibility: Boolean) : Fragment() {
    private val getData: Array<ModelOrders> =
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View
        var orientation = activity?.resources?.configuration?.orientation
        v = if (orientation == Configuration.ORIENTATION_PORTRAIT){
            inflater.inflate(R.layout.fragment_home, container, false)
        }else{
            inflater.inflate(R.layout.home_fragment_landscape, container, false)
        }
        val reciclerView = v.findViewById<AutofitRecyclerView>(R.id.my_grid_view_list)
        reciclerView.setHasFixedSize(true)
        reciclerView.adapter = DemoAdapter(mContext, getData)
        if (visibility) {
            v.findViewById<LinearLayout>(R.id.indicators).visibility = View.VISIBLE
        } else {
           v.findViewById<LinearLayout>(R.id.indicators).visibility = View.GONE
        }
        return v
    }
}