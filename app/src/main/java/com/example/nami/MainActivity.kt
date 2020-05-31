package com.example.nami

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.nami.adapter.SectionsAdapter
import com.example.nami.models.sections.SectionsResponse
import com.example.nami.presenters.SectionsPresenter
import com.example.nami.presenters.SectionsUI
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), SectionsUI {
    private val presenter = SectionsPresenter(this)
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.actionSections()

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)


    }

    override fun showSection(data: SectionsResponse) {

        runOnUiThread {
            for (section in data.sections) {
                tabLayout!!.addTab(tabLayout!!.newTab().setText(section.name))
            }
            tabLayout?.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = SectionsAdapter(this, supportFragmentManager, tabLayout!!.tabCount,data.actions,data.legends.toTypedArray(),data.sections)

            viewPager!!.adapter = adapter

            viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

            tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager!!.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab) {

                }

                override fun onTabReselected(tab: TabLayout.Tab) {

                }
            })
        }}

    override fun showError(error: String) {
        runOnUiThread {
            Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
        }
    }
}
