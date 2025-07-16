package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Bank_Confirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_confirm)
        val Drawer_money=intent.getIntExtra("Drawer",0)
        val have_money=intent.getIntExtra("money",0)//デフォルト値を設定
        val tvHaveMoney = findViewById<TextView>(R.id.has_money_conf)
        val total_money = Drawer_money + have_money
        tvHaveMoney.text=total_money.toString()
        val BankMessage =findViewById<TextView>(R.id.bank_message)
        BankMessage.text = Drawer_money.toString() + getString(R.string.bank_message)






    }
    fun OnBackButtonClick(view: View){
        finish()
    }

}