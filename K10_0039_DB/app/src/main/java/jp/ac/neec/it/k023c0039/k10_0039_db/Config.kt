package jp.ac.neec.it.k023c0039.k10_0039_db

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Config : AppCompatActivity() {
    private lateinit var _helper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)
        _helper = DatabaseHelper(this@Config)
        val name = findViewById<EditText>(R.id.et_name)
        val price = findViewById<EditText>(R.id.et_price)
        val save = findViewById<View>(R.id.btn_save)
        save.setOnClickListener {
            val itemName = name.text.toString()
            val itemPrice: Int? = price.text.toString().toIntOrNull()
            addItem(itemName, itemPrice)
            name.text.clear()
            price.text.clear()
        }


    }
    private fun addItem(name:String,price:Int?){
        val db = _helper.writableDatabase
        if (name.isBlank()) {
            return
        }
        if (price == null) {
            // 【価格が未入力の場合】→ 削除処理
            val sqlDelete = "DELETE FROM ItemList WHERE name = ?"
            val stmt = db.compileStatement(sqlDelete)
            stmt.bindString(1, name)
            stmt.executeUpdateDelete()

        } else {
            // 【価格が入力されている場合】→ 登録処理
            val sqlInsert = "INSERT INTO ItemList (name, price) VALUES (?, ?)"
            val stmt = db.compileStatement(sqlInsert)
            stmt.bindString(1, name)
            stmt.bindLong(2, price.toLong())
            stmt.executeInsert()
        }



    }
    fun OnBackButtonClick(view: View){
        finish()
    }
}