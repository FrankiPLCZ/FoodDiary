package com.example.fooddiary.Fragmenty

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooddiary.Adaptery.FoodAdapter
import com.example.fooddiary.Adaptery.ViewPagerAdapter
import com.example.fooddiary.Databases.DatabaseHelper2
import com.example.fooddiary.R
import com.example.fooddiary.databinding.DialogLayoutBinding
import com.example.fooddiary.databinding.FragmentDishesBinding
import com.example.fooddiary.databinding.FragmentFruitsBinding

class DISHES(val c:Context) : Fragment() {

private lateinit var b:FragmentDishesBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b =  FragmentDishesBinding.inflate(inflater,container,false)
        val rootView: View = b.root

        setUpTabs()

        return rootView

}

    fun setUpTabs() {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(Dish_Breakfast(c),"Breakfast")
        adapter.addFragment(Dish_Lunch(c),"Lunch")
        adapter.addFragment(Dish_Dinner(c),"Dinner")
        adapter.addFragment(Dish_Snack(c),"Snack")
        adapter.addFragment(Dish_Supper(c),"Supper")

        b.viewPager.adapter = adapter
        b.tabs.setupWithViewPager(b.viewPager)

        b.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_breakfast2)
        b.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_lunch)
        b.tabs.getTabAt(2)!!.setIcon(R.drawable.icon_meat)
        b.tabs.getTabAt(3)!!.setIcon(R.drawable.ic_popcorn)
        b.tabs.getTabAt(4)!!.setIcon(R.drawable.ic_supper)
    }
}