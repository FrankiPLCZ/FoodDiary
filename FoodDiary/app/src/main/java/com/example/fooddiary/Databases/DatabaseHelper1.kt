package com.example.fooddiary.Databases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
//-------------------------------Dane w bazie----------------------------------------
object TableInfo: BaseColumns{
    const val TABLE_NAME = "Datag"
    const val TABLE_COLUMN_NAME = "name"
    const val TABLE_COLUMN_WEIGHT = "weight"
    const val TABLE_COLUMN_AGE = "age"
    const val TABLE_COLUMN_HEIGHT = "height"
    const val TABLE_COLUMN_ACTIVITY = "activity"
    const val TABLE_COLUMN_SEX = "sex"
    const val TABLE_COLUMN_KCAL = "kcal"
    const val TABLE_COLUMN_PROTEIN = "protein"
}
//----------------------------------Komendy uproszczone------------------------------------------------
object BasicCommand {
    const val SQL_CREATE_TABLE:String =
        "CREATE TABLE ${TableInfo.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_WEIGHT} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_AGE} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_HEIGHT} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_ACTIVITY} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_SEX} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_KCAL} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_PROTEIN} TEXT NOT NULL)"

    const val  SQL_DELETE_TABLE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME}"
}

class DatabaseHelper1(context: Context):SQLiteOpenHelper(context,TableInfo.TABLE_NAME, null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(BasicCommand.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(BasicCommand.SQL_DELETE_TABLE)
        onCreate(p0)
    }


}