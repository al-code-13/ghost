package com.example.nami

import SectionFragment
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
import com.example.nami.models.sections.Legend
import com.example.nami.presenters.SectionPresenter
import com.example.nami.presenters.SectionUI


class HomeFragment(
    private val mContext: Context,
    private val legendList: List<Legend>,
    private val sectionid: Long
) : Fragment(), SectionUI {
    private val presenter = SectionPresenter(this)
    var reciclerView: AutofitRecyclerView? = null
    private var adapter: IndicatorsAdapter? = null
    private var token = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferences =
            this.activity!!.getSharedPreferences("localStorage", Context.MODE_PRIVATE)

        token = preferences.getString("token", "localStorage").toString()
        Log.i("TOKENENLAOTRAPARTE", token)
        presenter.actionSection(
            token,
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
        adapter = IndicatorsAdapter(mContext, legendList)
        val gridView = v.findViewById<GridView>(R.id.gridItems)
        gridView?.adapter = adapter
        return v
    }


    override fun showData(data: SectionFragment) {

        activity?.runOnUiThread {
            reciclerView?.adapter = DemoAdapter(mContext, " ", data.orders, legendList)
        }
    }

    override fun showError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(mContext, error, Toast.LENGTH_LONG).show()
        }
    }
}