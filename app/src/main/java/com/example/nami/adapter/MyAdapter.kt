package com.example.nami.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nami.HomeFragment


@Suppress("DEPRECATION")
class MyAdapter(private val myContext: Context, fm: FragmentManager, var totalTabs: Int) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            HomeFragment(myContext,true)
        } else {
            HomeFragment(myContext,false)

        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}