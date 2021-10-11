package com.example.fooddiary.Fragmenty

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooddiary.Adaptery.FoodAdapter
import com.example.fooddiary.Adaptery.ViewPagerAdapter
import com.example.fooddiary.Databases.DatabaseHelper2
import com.example.fooddiary.R
import com.example.fooddiary.databinding.DialogLayoutBinding
import com.example.fooddiary.databinding.FragmentDishesBinding
import com.example.fooddiary.databinding.FragmentFoodBinding
import com.example.fooddiary.databinding.FragmentFruitsBinding

class FOOD(val c:Context) : Fragment() {

    private lateinit var b:FragmentFoodBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b =  FragmentFoodBinding.inflate(inflater,container,
                false)
        val rootView: View = b.root

        setUpTabs()

        return rootView

    }

    fun setUpTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(Fruits(c),"")
        adapter.addFragment(Vegetables(c),"")
        adapter.addFragment(Meat(c),"")
        adapter.addFragment(Dairy(c),"")
        adapter.addFragment(Bread(c),"")
        adapter.addFragment(Other(c),"")
        adapter.addFragment(Own(c),"")

        b.viewPager.adapter = adapter
        b.tabs.setupWithViewPager(b.viewPager)

        b.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_apple)
        b.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_brocolli)
        b.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_meata)
        b.tabs.getTabAt(3)!!.setIcon(R.drawable.ic_dairy)
        b.tabs.getTabAt(4)!!.setIcon(R.drawable.ic_bread)
        b.tabs.getTabAt(5)!!.setIcon(R.drawable.ic_other2)
        b.tabs.getTabAt(6)!!.setIcon(R.drawable.ic_baseline_star_24)
    }
}