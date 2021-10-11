package com.example.fooddiary

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.View
import com.example.fooddiary.Databases.*
import com.example.fooddiary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        private lateinit var b: ActivityMainBinding
        private lateinit var intento : Intent
        private lateinit var dbHelper: DatabaseHelper1
        private lateinit var db: SQLiteDatabase
        private lateinit var dbHelper2: DatabaseHelper2
        private lateinit var db2: SQLiteDatabase
        private lateinit var eatent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(b.root)
        intento = Intent(applicationContext, DataInsertion::class.java)
        dbHelper = DatabaseHelper1(applicationContext)
        db  = dbHelper.writableDatabase
        dbHelper2 = DatabaseHelper2(applicationContext)
        db2  = dbHelper2.writableDatabase
        eatent = Intent(applicationContext,FoodLibrary::class.java)

    }


    override fun onResume() {
        super.onResume()

        val cur = db.query(TableInfo.TABLE_NAME, null, BaseColumns._ID + "=?", arrayOf("1"), null, null, null)
        if(cur.moveToFirst()) {
            if(!(cur.getString(1).isNullOrEmpty())){
                if(kcaleaten == 0.0)
                    kcaleaten = cur.getDouble(7)
                if(proteineaten==0.0)
                    proteineaten = cur.getDouble(8)
                b.hello.text = "HELLO ${cur.getString(1).toUpperCase()}"
                val mx = if(cur.getString(6)=="Male")
                            ((cur.getDouble(2) *10 + cur.getDouble(4)*6.25 - cur.getDouble(3) * 5 + 5)*cur.getDouble(5)).toInt()
                        else
                            ((cur.getDouble(2) *10 + cur.getDouble(4)*6.25 - cur.getDouble(3) * 5 -161)*cur.getDouble(5)).toInt()
                b.Kcal.max = mx
                b.kcaltextstats.text = "${kcaleaten.toInt()}/$mx"
                b.Kcal.progress = kcaleaten.toInt()
                //if(BAZA<mx)b.kcalstatus.text=CHUDNIESZ
                val maxp = (cur.getDouble(5) * cur.getDouble(2)).toInt()
                b.Protein.max = maxp
                b.proteintextstats.text = "${proteineaten.toInt()}/$maxp"
                b.Protein.progress = proteineaten.toInt()
                //if(BAZA<mx)b.proteinstatus.text=BICKIROSNO
            }

        }
        else{
            startActivity(intento)
            val cur2 = db2.query(TableInfo2.TABLE_NAME, null, null, null, null, null, null)
            if(!cur2.moveToFirst()){
                for(i in 0..7){
                    val value = ContentValues()
                    value.put("name","option")
                    value.put("protein", "0")
                    value.put("kcal","0")
                    value.put("type", i.toString())
                    value.put("img","android.resource://com.example.fooddiary/drawable/ic_baseline_add_24")

                    db2.insertOrThrow(TableInfo2.TABLE_NAME,null,value)
                }
            fill(databaseimport,db2)

            }
            }

    }

    fun edit(view: View) {
        startActivity(intento)
        check =true
    }
    companion object keepdata{
        var check = false
        var proteineaten:Double = 0.0
        var kcaleaten:Double = 0.0
        var position:Int = 0
        var check2 = false
        var type:String = "0"
    }

    fun Add(view: View) {
        startActivity(eatent)
    }

    override fun onStop() {
        super.onStop()
        val value = ContentValues()
        value.put("kcal","${kcaleaten}")
        value.put("protein","${proteineaten}")
        db.update(TableInfo.TABLE_NAME,value,null,null)
    }
    fun clear(view: View){
        proteineaten = 0.0
        kcaleaten = 0.0
        b.Protein.progress = 0
        b.Kcal.progress = 0
    }
}