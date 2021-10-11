package com.example.fooddiary.Databases

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import com.example.fooddiary.R

val databaseimport:Array<Array<String>> = arrayOf(
        //arrayOf("name","protein","kcal","type","android.resource://com.example.fooddiary/drawable/ic_baseline_add_24"),
        arrayOf("Banana","1.1","88.7","1","android.resource://com.example.fooddiary/" + R.mipmap.image_bananx.toString()),
        arrayOf("Apple","0.3","52.1","1","android.resource://com.example.fooddiary/" + R.mipmap.image_apple.toString()),
        arrayOf("Orange","0.9","47.1","1","android.resource://com.example.fooddiary/" + R.mipmap.image_orange.toString()),
        arrayOf("Lemon","1.1","28.9","1","android.resource://com.example.fooddiary/" + R.mipmap.image_lemon.toString()),
        arrayOf("Grapes","0.6","66.9","1","android.resource://com.example.fooddiary/" + R.mipmap.image_grapes.toString()),
        arrayOf("Pinapple","0.5","50","1","android.resource://com.example.fooddiary/" + R.mipmap.image_pineapple.toString()),
        arrayOf("Coconut","3.3","354","1","android.resource://com.example.fooddiary/" + R.mipmap.image_coconut.toString()),

        arrayOf("Tomato","0.9","17.7","2","android.resource://com.example.fooddiary/" + R.mipmap.image_tomato.toString()),
        arrayOf("Cucumber","0.7","15.5","2","android.resource://com.example.fooddiary/" + R.mipmap.image_cucumber.toString()),
        arrayOf("Pepper","14","282.3","2","android.resource://com.example.fooddiary/" + R.mipmap.image_pepper.toString()),

        arrayOf("Chicken breast","31","164.9","3","android.resource://com.example.fooddiary/" + R.mipmap.image_chicken_breast.toString()),

        arrayOf("Milk","3.4","42.3","4","android.resource://com.example.fooddiary/" + R.mipmap.image_milk.toString()),
        arrayOf("Eggs","13","155.1","4","android.resource://com.example.fooddiary/" + R.mipmap.image_eggs.toString()),

        arrayOf("Wheat bread","13","247.1","5","android.resource://com.example.fooddiary/" + R.mipmap.image_wheat_bread.toString()),
        arrayOf("Rye bread","9","259.1","5","android.resource://com.example.fooddiary/" + R.mipmap.image_rye_bread.toString()),

        arrayOf("Pasta","5","131","6","android.resource://com.example.fooddiary/" + R.mipmap.image_pasta.toString()),
        arrayOf("Walnut","15","654.5","6","android.resource://com.example.fooddiary/" + R.mipmap.image_wallnut.toString()),

        arrayOf("Mango","0.8","59.8","1","android.resource://com.example.fooddiary/" + R.mipmap.image_mango.toString())

)
fun fill(data:Array<Array<String>>,db: SQLiteDatabase){
    val value = ContentValues()
    var uri: Uri
    data.forEach {
        value.put("name",it[0])
        value.put("protein",it[1])
        value.put("kcal",it[2])
        value.put("type",it[3])
        uri = Uri.parse( it[4])
        value.put("img",uri.toString())
        value.put("x","0")
        db.insertOrThrow(TableInfo2.TABLE_NAME, null,value)
    }}