package com.example.nami.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.nami.HomeFragment


@Suppress("DEPRECATION")
class MyAdapter(private val myContext: Context, fm: FragmentManager, var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                HomeFragment(myContext)
            }
            1 -> {
                HomeFragment(myContext)
            }
            2 -> {
                HomeFragment(myContext)
            }
            3 -> {
                HomeFragment(myContext)
            }

            else -> HomeFragment(myContext)
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}