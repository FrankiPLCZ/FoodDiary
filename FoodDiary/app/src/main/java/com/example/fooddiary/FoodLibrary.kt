package com.example.fooddiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fooddiary.Adaptery.ViewPagerAdapter
import com.example.fooddiary.Fragmenty.*
import com.example.fooddiary.databinding.ActivityFoodlibraryBinding

class FoodLibrary : AppCompatActivity() {
        private lateinit var b:ActivityFoodlibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityFoodlibraryBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(b.root)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(DISHES(this),"Dishes")
        adapter.addFragment(FOOD(this),"Food")
        adapter.addFragment(CREATE_DISH(this),"New dish")

        b.viewPager.adapter = adapter
        b.tabs.setupWithViewPager(b.viewPager)

        b.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_dishes)
        b.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_food)
        b.tabs.getTabAt(2)!!.setIcon(R.drawable.icon_cook)
    }

    override fun onResume() {
        super.onResume()
        MainActivity.check2 = false
    }
}