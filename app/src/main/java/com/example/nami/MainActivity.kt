package com.example.nami

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.nami.adapter.MyAdapter
import com.example.nami.models.sections.SectionsResponse
import com.example.nami.presenters.SectionsPresenter
import com.example.nami.presenters.SectionsUI
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), SectionsUI {
    private val presenter = SectionsPresenter(this)
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private var token:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference =  getSharedPreferences("localStorage", Context.MODE_PRIVATE)
        token = sharedPreference.getString("token","localStorage").toString()
        presenter.actionSections(token)

        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)


    }

    override fun showSection(data: SectionsResponse) {

        runOnUiThread {
            for (section in data.sections) {
                tabLayout!!.addTab(tabLayout!!.newTab().setText(section.name))
            }
            tabLayout?.tabGravity = TabLayout.GRAVITY_FILL

            val adapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount,data.legends.toTypedArray(),data.sections)

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
