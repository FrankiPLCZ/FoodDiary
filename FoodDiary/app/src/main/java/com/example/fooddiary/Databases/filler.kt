package com.example.fooddiary.Databases

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

val databaseimport:Array<Array<String>> = arrayOf(
        //arrayOf("name","protein","kcal","type","android.resource://com.example.fooddiary/drawable/ic_baseline_add_24"),
        arrayOf("Banana","1.1","88.7","1","android.resource://com.example.fooddiary/drawable/image_bananana"),
        arrayOf("Apple","0.3","52.1","1","android.resource://com.example.fooddiary/drawable/image_apple"),
        arrayOf("Orange","0.9","47.1","1","android.resource://com.example.fooddiary/drawable/image_orange"),
        arrayOf("Lemon","1.1","28.9","1","android.resource://com.example.fooddiary/drawable/image_lemon"),
        arrayOf("Grapes","0.6","66.9","1","android.resource://com.example.fooddiary/drawable/image_grapes"),
        arrayOf("Pinapple","0.5","50","1","android.resource://com.example.fooddiary/drawable/image_pinapple"),
        arrayOf("Coconut","3.3","354","1","android.resource://com.example.fooddiary/drawable/image_coconut"),

        arrayOf("Tomato","0.9","17.7","2","android.resource://com.example.fooddiary/drawable/image_tomato"),
        arrayOf("Cucumber","0.7","15.5","2","android.resource://com.example.fooddiary/drawable/image_cucumber"),
        arrayOf("Pepper","14","282.3","2","android.resource://com.example.fooddiary/drawable/image_pepper"),

        arrayOf("Chicken breast","31","164.9","3","android.resource://com.example.fooddiary/drawable/image_chicken_breast"),

        arrayOf("Milk","3.4","42.3","4","android.resource://com.example.fooddiary/drawable/image_milk"),
        arrayOf("Eggs","13","155.1","4","android.resource://com.example.fooddiary/drawable/image_eggs"),

        arrayOf("Wheat bread","13","247.1","5","android.resource://com.example.fooddiary/drawable/image_wheat_bread"),
        arrayOf("Rye bread","9","259.1","5","android.resource://com.example.fooddiary/drawable/image_rye_bread"),

        arrayOf("Pasta","5","131","6","android.resource://com.example.fooddiary/drawable/image_pasta"),
        arrayOf("Walnut","15","654.5","6","android.resource://com.example.fooddiary/drawable/image_walnut"),

        arrayOf("Mango","0.8","59.8","1","android.resource://com.example.fooddiary/drawable/image_mango")

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
        db.insertOrThrow(TableInfo2.TABLE_NAME, null,value)
    }}