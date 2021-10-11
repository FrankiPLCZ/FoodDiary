package com.example.fooddiary

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.BaseColumns
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.fooddiary.Databases.DatabaseHelper2
import com.example.fooddiary.Databases.TableInfo
import com.example.fooddiary.Databases.TableInfo2
import com.example.fooddiary.databinding.ActivityAddNewFoodBinding
import com.theartofdev.edmodo.cropper.CropImage
import java.io.InputStream

class AddNewFood : AppCompatActivity() {
    private lateinit var b: ActivityAddNewFoodBinding
    private lateinit var dbHelper: DatabaseHelper2
    private lateinit var db: SQLiteDatabase
    private lateinit var type : String
    private lateinit var uri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        b = ActivityAddNewFoodBinding.inflate(layoutInflater)
        setContentView(b.root)
        uri = Uri.EMPTY
        type= " "

        dbHelper = DatabaseHelper2(applicationContext)
        db  = dbHelper.writableDatabase
        setlisteners()
        if(MainActivity.check2)
        {
            val cur = db.query(TableInfo2.TABLE_NAME, null, TableInfo2.TABLE_COLUMN_TYPE+" LIKE ?", arrayOf("${MainActivity.type}"), null, null, null)
                if(cur.moveToFirst()){
                    cur.moveToPosition(MainActivity.position)
                    if(!(cur.getString(1).isNullOrEmpty())){
                    b.Name.setText(cur.getString(1))
                    b.Proteinin100.setText(cur.getString(2))
                    b.Kcal100.setText(cur.getString(3))
                }
        }}

    }
    fun InsertDB2(v: View){
        val name = b.Name.text.toString()
        val protein = b.Proteinin100.text.toString()
        val kcal = b.Kcal100.text.toString()

        if(!name.isNullOrEmpty()&&!protein.isNullOrEmpty()&&!kcal.isNullOrEmpty()&&!Uri.EMPTY.equals(uri)&& type!=" "){
            val value = ContentValues()
            value.put("name", name)
            value.put("protein", protein)
            value.put("kcal", kcal)
            value.put("type", type)
            value.put("img", uri.toString())
            if (!MainActivity.check2)
                db.insertOrThrow(TableInfo2.TABLE_NAME, null, value)
            else
                db.update(TableInfo2.TABLE_NAME,value,"_id = ?", arrayOf(MainActivity.position.toString()))

            finish()
        }else Toast.makeText(applicationContext, "Please fill all the information", Toast.LENGTH_SHORT).show()
    }
    fun change(view: View) {
        supportActionBar!!.show()
        CropImage.activity().start(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode== CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            val result = CropImage.getActivityResult(data)
            supportActionBar!!.hide()
            if (resultCode== RESULT_OK){
                uri = result.uri
                val inputStream: InputStream? = getContentResolver().openInputStream(uri)
                val dr = Drawable.createFromStream(inputStream, uri.toString())
                b.img.background = dr
            }
        }
    }
    fun clearRadioChecked() {
        b.own.setChecked(false)
        b.Bread.setChecked(false)
        b.Other.setChecked(false)
        b.dairy.setChecked(false)
        b.fruits.setChecked(false)
        b.meat.setChecked(false)
        b.vegetable.setChecked(false)
    }
    fun setlisteners(){
        b.own.setOnClickListener {
            clearRadioChecked()
            b.own.setChecked(true)
            type = it.tag.toString()

        }
        b.Bread.setOnClickListener {
            clearRadioChecked()
            b.Bread.setChecked(true)
            type = it.tag.toString()

        }
        b.Other.setOnClickListener {
            clearRadioChecked()
            b.Other.setChecked(true)
            type = it.tag.toString()

        }
        b.dairy.setOnClickListener {
            clearRadioChecked()
            b.dairy.setChecked(true)
            type = it.tag.toString()

        }
        b.fruits.setOnClickListener {
            clearRadioChecked()
            b.fruits.setChecked(true)
            type = it.tag.toString()

        }
        b.meat.setOnClickListener {
            clearRadioChecked()
            b.meat.setChecked(true)
            type = it.tag.toString()

        }
        b.vegetable.setOnClickListener {
            clearRadioChecked()
            b.vegetable.setChecked(true)
            type = it.tag.toString()

        }
    }


}