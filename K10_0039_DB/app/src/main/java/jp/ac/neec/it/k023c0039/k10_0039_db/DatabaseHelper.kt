package jp.ac.neec.it.k023c0039.k10_0039_db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context):
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "SevenItem.db"
        private const val DATABASE_VERSION = 1

    }
    override fun onCreate(db: SQLiteDatabase) {
        val sb=StringBuilder()
        sb.append("CREATE TABLE ItemList (")
        sb.append("_id INTEGER PRIMARY KEY , ")
        sb.append("name TEXT,")
        sb.append("price INTEGER")
        sb.append(");")
        val sql = sb.toString()
        db.execSQL(sql)


    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}