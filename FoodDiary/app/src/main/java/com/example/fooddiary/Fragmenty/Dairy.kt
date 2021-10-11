package com.example.fooddiary.Fragmenty

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fooddiary.Adaptery.FoodAdapter
import com.example.fooddiary.Databases.DatabaseHelper2
import com.example.fooddiary.R
import com.example.fooddiary.databinding.DialogLayoutBinding
import com.example.fooddiary.databinding.FragmentDairyBinding
import com.example.fooddiary.databinding.FragmentFruitsBinding

class Dairy(val c:Context) : Fragment() {

private lateinit var b:FragmentDairyBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        b =  FragmentDairyBinding.inflate(inflater,container,false)
        val rootView: View = b.root
        return rootView
    }
    override fun onResume() {
        super.onResume()

        val dbHelper = DatabaseHelper2(c)
        val db = dbHelper.writableDatabase

        b.rv.layoutManager = GridLayoutManager(c,3)
        b.rv.adapter = FoodAdapter(c,db,"4")
    }
}