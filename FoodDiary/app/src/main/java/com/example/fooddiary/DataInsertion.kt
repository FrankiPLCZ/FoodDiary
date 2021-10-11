package com.example.fooddiary

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isEmpty
import com.example.fooddiary.Databases.DatabaseHelper1
import com.example.fooddiary.Databases.TableInfo
import com.example.fooddiary.databinding.ActivityDataInsertionBinding
import com.example.fooddiary.databinding.ActivityMainBinding

class DataInsertion : AppCompatActivity() {
        private lateinit var b: ActivityDataInsertionBinding
        private lateinit var dbHelper:DatabaseHelper1
        private lateinit var db: SQLiteDatabase
        private lateinit var activity : String
        private lateinit var sex:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityDataInsertionBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(b.root)

        dbHelper = DatabaseHelper1(applicationContext)
        db  = dbHelper.writableDatabase

        val cur = db.query(TableInfo.TABLE_NAME, null, BaseColumns._ID + "=?", arrayOf("1"), null, null, null)
        if(cur.moveToFirst()) {
            if(!(cur.getString(1).isNullOrEmpty())){
                b.Name.setText(cur.getString(1))
                b.Weight.setText(cur.getString(2))
                b.Age.setText(cur.getString(3))
                b.Height.setText(cur.getString(4))
                /*
                * Spocik na jakis kotlin kod zeby chycic radio buttona
                * */
            }}

        b.group.setOnCheckedChangeListener { radioGroup, i ->
            val RB:RadioButton = findViewById(i)
            activity = RB.tag.toString()
        }
        b.groupsexhehe.setOnCheckedChangeListener { radioGroup, i ->
            val RB:RadioButton = findViewById(i)
            sex = RB.text.toString()
        }

    }
    fun InsertDB(v:View){
        val name = b.Name.text.toString()
        val weight = b.Weight.text.toString()
        val age = b.Age.text.toString()
        val height = b.Height.text.toString()
        if(!name.isNullOrEmpty()&&!weight.isNullOrEmpty()&&!age.isNullOrEmpty()&&!height.isNullOrEmpty()&&b.groupsexhehe.checkedRadioButtonId!=-1&&b.group.checkedRadioButtonId!=-1){
            val value = ContentValues()
            value.put("name",name)
            value.put("weight", weight)
            value.put("age",age)
            value.put("height", height)
            value.put("activity",activity)
            value.put("sex",sex)

            if (!MainActivity.check){
                value.put("kcal","0.0")
                value.put("protein","0.0")
                db.insertOrThrow(TableInfo.TABLE_NAME,null,value)
            }
            else
                db.update(TableInfo.TABLE_NAME,value,null,null)
            finish()
        }else Toast.makeText(applicationContext,"Please fill all the information", Toast.LENGTH_SHORT).show()
    }

}