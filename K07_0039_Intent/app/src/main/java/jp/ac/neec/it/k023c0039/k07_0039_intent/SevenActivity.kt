package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SevenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven)

        val name=intent.getStringExtra("Name")
        val has_money = intent.getIntExtra("money", 0) // 第2引数はデフォルト値です
        val textView = findViewById<TextView>(R.id.has_money)
        textView.text = has_money.toString()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val menuList : MutableList<MutableMap<String,String>> = mutableListOf()

        val menus : MutableList<String> = mutableListOf(
            "直火焼き牛カルビ弁当", "銀鮭の塩焼き弁当", "ミートソーススパゲッティ",
            "炙り焼きチキン弁当", "豚ロース生姜焼き弁当", "麻婆豆腐丼",
            "三元豚のロースかつ丼", "鶏そぼろごはん", "明太子おにぎり", "ツナマヨおにぎり"
        )
        val prices : MutableList<String> = mutableListOf(
            "598円", "550円", "430円",
            "530円", "560円", "470円",
            "598円", "420円", "150円", "140円"

        )

        for(i in menus.indices){
            var menu = mutableMapOf("name" to menus[i],"price" to prices[i])
            menuList.add(menu)
        }

        val from = arrayOf("name","price")
        val to = intArrayOf(android.R.id.text1,android.R.id.text2)
        val adapter=
            SimpleAdapter(this@SevenActivity,menuList,android.R.layout.simple_list_item_2,from, to)
        lvMenu.adapter=adapter
        lvMenu.onItemClickListener=ListItemClickListener()


    }
    private inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item=parent.getItemAtPosition(position) as MutableMap<String,String>
            val menuName=item["name"]
            val menuPrice=item["price"]
            val has_money = intent.getIntExtra("money", 0)

            val intent3Confirm = Intent(this@SevenActivity,Seven_Confirm ::class.java)
            intent3Confirm.putExtra("menuName",menuName)
            intent3Confirm.putExtra("menuPrice",menuPrice)
            intent3Confirm.putExtra("hasMoney", has_money)
            startActivity(intent3Confirm)
            finish()


        }
    }
}