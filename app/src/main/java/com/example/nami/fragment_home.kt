package com.example.nami

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nami.adapter.DemoAdapter
import com.example.nami.adapter.IndicatorsAdapter
import com.example.nami.model.ItemIndicators
import com.example.nami.models.auth.sections.Legend
import com.example.nami.models.auth.sections.SectionFragment
import com.example.nami.models.auth.sections.SectionResponse
import com.example.nami.presenters.SectionPresenter
import com.example.nami.presenters.SectionUI

class HomeFragment(
    private val mContext: Context,
    private val legendList: List<Legend>,
    private val sectionid: Long
) : Fragment(), SectionUI {
    private val presenter = SectionPresenter(this)
    var reciclerView: AutofitRecyclerView? = null
    private var adapter: IndicatorsAdapter ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.actionSection(
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Mjk5LCJpYXQiOjE1OTAwNzE2MzMsImV4cCI6MTU5MDE1ODAzM30.lx4RxBJ7ekrOjwjq_MLD13bJ777mIt7S0Fk8Cenh3cc",
            sectionid
        )
        val v: View
        val orientation = activity?.resources?.configuration?.orientation
        v = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            inflater.inflate(R.layout.fragment_home, container, false)
        } else {
            inflater.inflate(R.layout.home_fragment_landscape, container, false)
        }
        reciclerView = v.findViewById<AutofitRecyclerView>(R.id.my_grid_view_list)
        //reciclerView.setHasFixedSize(true)
        adapter = IndicatorsAdapter(mContext,legendList)
        val gridView = v.findViewById<GridView>(R.id.gridItems)
        gridView?.adapter = adapter
        return v
    }


    override fun showData(data: SectionFragment) {

        activity?.runOnUiThread {

            reciclerView?.adapter = DemoAdapter(mContext," ", data.orders.list)
        }
    }

    override fun showError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(mContext, error, Toast.LENGTH_LONG).show()
        }
    }
}