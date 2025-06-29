package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameStoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_store)
        val name=intent.getStringExtra("Name")
        val has_money = intent.getIntExtra("money", 0) // 第2引数はデフォルト値です
        val textView = findViewById<TextView>(R.id.has_money)
        textView.text = has_money.toString()
        val lvMenu = findViewById<ListView>(R.id.lv_game_Menu)
        val menuList : MutableList<MutableMap<String,String>> = mutableListOf()

        val menus : MutableList<String> = mutableListOf(
            "ELDEN RING",
            "ゼルダの伝説 ティアーズ オブ ザ キングダム",
            "FINAL FANTASY VII REBIRTH",
            "モンスターハンター：ワールド",
            "あつまれ どうぶつの森",
            "マリオカート8 デラックス",
            "ポケットモンスター スカーレット",
            "グランツーリスモ７",
            "Minecraft",
            "スプラトゥーン3"
        )
        val prices : MutableList<String> = mutableListOf(
            "8700円",
            "7920円",
            "9878円",
            "3960円",
            "5980円",
            "6578円",
            "6500円",
            "7980円",
            "3960円",
            "6000円"

        )

        for(i in menus.indices){
            var menu = mutableMapOf("name" to menus[i],"price" to prices[i])
            menuList.add(menu)
        }

        val from = arrayOf("name","price")
        val to = intArrayOf(android.R.id.text1,android.R.id.text2)
        val adapter=
            SimpleAdapter(this@GameStoreActivity,menuList,android.R.layout.simple_list_item_2,from, to)
        lvMenu.adapter=adapter
        lvMenu.onItemClickListener=ListItemClickListener()


    }
    private inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item=parent.getItemAtPosition(position) as MutableMap<String,String>
            val menuName=item["name"]
            val menuPrice=item["price"]
            val has_money = intent.getIntExtra("money", 0)

            val intent3Confirm = Intent(this@GameStoreActivity,Game_Confirm ::class.java)
            intent3Confirm.putExtra("menuName",menuName)
            intent3Confirm.putExtra("menuPrice",menuPrice)
            intent3Confirm.putExtra("hasMoney", has_money)
            startActivity(intent3Confirm)


        }
    }
    fun OnBackButtonClick(view: View){
        finish()
    }
}