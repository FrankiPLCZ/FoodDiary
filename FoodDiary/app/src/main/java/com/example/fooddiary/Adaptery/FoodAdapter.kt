package com.example.fooddiary.Adaptery

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.drawable.Drawable
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.fooddiary.AddNewFood
import com.example.fooddiary.Databases.TableInfo2
import com.example.fooddiary.Fragmenty.DialogPopUp
import com.example.fooddiary.MainActivity
import com.example.fooddiary.R
import java.io.InputStream


class FoodAdapter(val context: Context, val db: SQLiteDatabase, val type: String) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val food_lay = layoutInflater.inflate(R.layout.food_layout, parent, false)
        return MyViewHolder(food_lay)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val cursor = db.query(TableInfo2.TABLE_NAME, null, TableInfo2.TABLE_COLUMN_TYPE + " LIKE ?", arrayOf("${type}"), null, null, null)

        if(cursor.moveToFirst()) {
                cursor.moveToPosition(position)
            if(cursor.getString(1)=="option")
            {
                holder.imgM.setImageURI(cursor.getString(5).toUri())
                holder.imgM.setOnClickListener {
                    val intent = Intent(context, AddNewFood::class.java)
                    context.startActivity(intent)
                }
            }else{
                //holder.imgM.setImageURI(cursor.getString(5).toUri())
                    if (cursor.getString(6)=="1"){
                        val inputStream: InputStream? = context.getContentResolver().openInputStream(cursor.getString(5).toUri())
                        val dr = Drawable.createFromStream(inputStream, cursor.getString(5))
                        holder.imgM.background = dr
                    }else
                        holder.imgM.setImageURI(cursor.getString(5).toUri())

                holder.imgM.setOnClickListener {
                    val dialog = DialogPopUp(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(5).toUri(),cursor.getString(6))
                    val manager = (context as AppCompatActivity).supportFragmentManager
                    dialog.show(manager, "customDialog")
                }
                holder.imgM.setOnLongClickListener(object : View.OnLongClickListener {
                    override fun onLongClick(p0: View?): Boolean {
                        MainActivity.position = position
                        MainActivity.type = type
                        MainActivity.check2 = true
                        val intent = Intent(context, AddNewFood::class.java)
                        context.startActivity(intent)
                        return false
                    }
                })
            }
       }
    }

    override fun getItemCount(): Int {
        val cursor = db.query(TableInfo2.TABLE_NAME, null,
                TableInfo2.TABLE_COLUMN_TYPE + " LIKE ?",
                arrayOf("${type}"), null, null, null)
        val count = cursor.count
        cursor.close()
        return count
    }

}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val imgM:ImageView = view.findViewById(R.id.imago)

}

