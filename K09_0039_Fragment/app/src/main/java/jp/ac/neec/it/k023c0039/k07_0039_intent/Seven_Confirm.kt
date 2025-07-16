package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Seven_Confirm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven_confirm)
        val has_money = intent.getIntExtra("hasMoney", 0)
        val menuName=intent.getStringExtra("menuName")
        val menuPrice=intent.getStringExtra("menuPrice")
        val textView = findViewById<TextView>(R.id.has_money_conf)
        textView.text = has_money.toString()
        val cleanPriceString = menuPrice?.replace("円", "")
        val intPrice = cleanPriceString?.toIntOrNull()
        if (intPrice != null && menuName != null) {
            canAffordItem(has_money, intPrice,menuName)
        }
    }
    fun canAffordItem(money:Int,menuPrice:Int, menuName:String){
        if (money<=menuPrice){
            val textView = findViewById<TextView>(R.id.seven_message)
            textView.text = getString(R.string.conf_text_false)
        }
        else if(money>=menuPrice){
            val textView = findViewById<TextView>(R.id.seven_message)
            textView.text = getString(R.string.conf_text_True)+ menuName.toString() + menuPrice.toString()
        }

    }

}