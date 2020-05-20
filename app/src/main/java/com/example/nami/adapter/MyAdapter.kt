package com.example.nami.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nami.HomeFragment
import com.example.nami.models.auth.sections.Legend
import com.example.nami.models.auth.sections.SectionResponse


@Suppress("DEPRECATION")
class MyAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    var totalTabs: Int,
    private val legendList: Array<Legend>,
    private val sectionsList: List<SectionResponse>
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return HomeFragment(
            myContext,
            legendSection(sectionsList[position].legends),
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