package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etName = findViewById<EditText>(R.id.et_name)
        val etMoney = findViewById<EditText>(R.id.et_money)
        findViewById<Button>(R.id.bt_seven).setOnClickListener {
            val name = etName.text.toString()
            val money = etMoney.text.toString().toIntOrNull()
            if (money != null) {
                SevenButtonClick(name, money)
            }
        }
        findViewById<Button>(R.id.bt_game).setOnClickListener {
            val name = etName.text.toString()
            val money = etMoney.text.toString().toIntOrNull()
            if(money != null) {
                GameButtonClick(name, money)
            }
        }


        findViewById<Button>(R.id.bt_bank).setOnClickListener {
            val name = etName.text.toString()
            val money = etMoney.text.toString().toIntOrNull()
            if(money!=null){
                BankButtonClick(name,money)
            }
        }
    }
    private fun SevenButtonClick(name:String,money:Int){
        val intent2Seven = Intent(this@MainActivity,SevenActivity::class.java)
        intent2Seven.putExtra("Name",name)
        intent2Seven.putExtra("money",money)
        startActivity(intent2Seven)
    }
    private fun GameButtonClick(name:String,money:Int){
        val intent3Game = Intent(this@MainActivity,GameStoreActivity::class.java)
        intent3Game.putExtra("Name",name)
        intent3Game.putExtra("money",money)
        startActivity(intent3Game)
    }
    private fun BankButtonClick(name:String,money:Int){
        val intent4Bank = Intent(this@MainActivity,BankActivity::class.java)
        intent4Bank.putExtra("Name",name)
        intent4Bank.putExtra("money",money)
        startActivity(intent4Bank)
    }
}