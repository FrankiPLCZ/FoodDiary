package com.example.fooddiary.Databases


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
//-------------------------------Dane w bazie----------------------------------------
object TableInfo3: BaseColumns{
    const val TABLE_NAME = "dishes"
    const val TABLE_COLUMN_NAME = "name"
    const val TABLE_COLUMN_PROTEIN = "protein"
    const val TABLE_COLUMN_KCAL = "kcal"
    const val TABLE_COLUMN_TYPE = "type"
    const val TABLE_COLUMN_IMG = "img"
    const val TABLE_COLUMN_ARRAY = "array"

}
//----------------------------------Komendy uproszczone------------------------------------------------
object BasicCommand3 {
    const val SQL_CREATE_TABLE:String =
            "CREATE TABLE ${TableInfo3.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                    "${TableInfo3.TABLE_COLUMN_NAME} TEXT NOT NULL," +
                    "${TableInfo3.TABLE_COLUMN_PROTEIN} TEXT NOT NULL," +
                    "${TableInfo3.TABLE_COLUMN_KCAL} TEXT NOT NULL," +
                    "${TableInfo3.TABLE_COLUMN_TYPE} TEXT NOT NULL," +
                    "${TableInfo3.TABLE_COLUMN_IMG} TEXT NOT NULL," +
                    "${TableInfo3.TABLE_COLUMN_ARRAY} TEXT NOT NULL)"

    const val  SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo3.TABLE_NAME}"
}

class DatabaseHelper3(context: Context):SQLiteOpenHelper(context,TableInfo3.TABLE_NAME, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(BasicCommand3.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(BasicCommand3.SQL_DELETE_TABLE)
        onCreate(p0)
    }


}