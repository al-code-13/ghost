package com.example.nami

import SectionResponse
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.nami.adapter.OrdersAdapter
import com.example.nami.adapter.IndicatorsAdapter
import com.example.nami.models.sections.Action
import com.example.nami.models.sections.Behavior
import com.example.nami.presenters.SectionPresenter
import com.example.nami.presenters.SectionUI


class SectionFragment(
    private val mContext: Context,
    private val legendList: List<Behavior>,
    private val actionList: List<Action>,
    private val sectionId: Int

) : Fragment(), SectionUI {
    private val presenter = SectionPresenter(this)
    private var reciclerView: AutofitRecyclerView? = null
    private var adapter: IndicatorsAdapter? = null
    private var itemsRefresh: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.actionSection(
            sectionId
        )
        val v: View
        val orientation = activity?.resources?.configuration?.orientation
        v = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            inflater.inflate(R.layout.fragment_home, container, false)
        } else {
            inflater.inflate(R.layout.home_fragment_landscape, container, false)
        }

        reciclerView = v.findViewById(R.id.my_grid_view_list)
        adapter = IndicatorsAdapter(mContext, legendList)
        val gridView = v.findViewById<GridView>(R.id.gridItems)
        gridView?.adapter = adapter

        itemsRefresh?.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                mContext,
                R.color.colorPrimaryDark
            )
        )
        itemsRefresh?.setColorSchemeColors(Color.BLUE)
        itemsRefresh?.setOnRefreshListener {
            presenter.actionSection(
                sectionId
            )
            gridView?.adapter = adapter
            Log.i("SI SE REFRESH", "SE")
            itemsRefresh!!.isRefreshing = false
        }
        return v
    }


    override fun showData(data: SectionResponse) {
        activity?.runOnUiThread {
            reciclerView?.adapter = OrdersAdapter(mContext, data.orders)
        }
    }

    override fun showError(error: String) {
        activity?.runOnUiThread {
            Toast.makeText(mContext, error, Toast.LENGTH_LONG).show()
        }
    }
}