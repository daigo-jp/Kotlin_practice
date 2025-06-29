package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BankActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
        val name=intent.getStringExtra("Name")
        val nametextView =findViewById<TextView>(R.id.bank_name)
        nametextView.text=name.toString()
        val has_money = intent.getIntExtra("money", 0) // 第2引数はデフォルト値です
        val moneytextView = findViewById<TextView>(R.id.tv_bank_has_money)
        moneytextView.text = has_money.toString()
        val etDrawer = findViewById<EditText>(R.id.et_drawer)

        findViewById<Button>(R.id.bank_conf).setOnClickListener {
            val Drawer_money = etDrawer.text.toString().toIntOrNull()
            if (Drawer_money == null || Drawer_money <= 0) {
                Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show()
            } else {
                BankConfirmButtonClick(has_money, Drawer_money)
            }
        }





    }
    private fun BankConfirmButtonClick(money:Int,Drawer_money:Int){
        val intent2Bank = Intent(this@BankActivity,Bank_Confirm::class.java)
        intent2Bank.putExtra("Drawer",Drawer_money)
        intent2Bank.putExtra("money",money)
        startActivity(intent2Bank)
        finish()
    }
}