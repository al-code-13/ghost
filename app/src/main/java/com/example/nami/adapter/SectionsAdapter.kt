package com.example.nami.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nami.HomeFragment
import com.example.nami.models.sections.Action
import com.example.nami.models.sections.Legend
import com.example.nami.models.sections.SectionResponse


@Suppress("DEPRECATION")
class MyAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    var totalTabs: Int,
    private val actionList:List<Action>,
    private val legendList: Array<Legend>,
    private val sectionsList: List<SectionResponse>
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return HomeFragment(
            myContext,
            legendSection(sectionsList[position].legends),
            actionList,
            sectionsList[position].id
        )
    }

    fun legendSection(indicators: List<Long>): List<Legend> {
        val newList = mutableListOf<Legend>()
        var i: Int = 0
        indicators.forEach {
            if (legendList[i].visible) {
                newList += legendList[i]
            }
            i++
        }
        return newList
    }

    override fun getCount(): Int {
        return totalTabs
    }
}