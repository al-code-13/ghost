package com.example.nami.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nami.SectionFragment
import com.example.nami.models.sections.Action
import com.example.nami.models.sections.Behavior
import com.example.nami.models.sections.Section


@Suppress("DEPRECATION")
class SectionsAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    var totalTabs: Int,
    private val actionList:List<Action>,
    private val legendList: Array<Behavior>,
    private val sectionsList: List<Section>
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return SectionFragment(
            myContext,
            legendSection(sectionsList[position].behaviors),
            actionList,
            sectionsList[position].id
        )
    }

    fun legendSection(indicators: List<Int>): List<Behavior> {
        val newList = mutableListOf<Behavior>()
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