package jp.ac.neec.it.k023c0039.k04_0039_listview

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        val gameItem = listOf(
            "ゼルダの伝説 ブレス オブ ザ ワイルド",
            "The Witcher 3: Wild Hunt",
            "UNDERTALE",
            "Minecraft",
            "Grand Theft Auto V",
            "Red Dead Redemption 2",
            "ペルソナ5 ザ・ロイヤル",
            "あつまれ どうぶつの森",
            "ELDEN RING",
            "Hades",
            "Stardew Valley",
            "スーパーマリオ オデッセイ",
            "Ghost of Tsushima",
            "NieR:Automata"
        )
        // ArrayAdapterは、リストのデータをListViewの各行にどのように表示するかを制御する
        //android.R.layout.simple_list_item_1 を使用しているため、各行の View はこのレイアウトから作られた TextView
        val adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, gameItem)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
    }
    // ListViewのアイテムクリックイベントを処理するための内部クラス
    // AdapterView.OnItemClickListenerインターフェースを実装する

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            // parent: クリックが発生したAdapterView (この場合はListView)
            // view: クリックされたアイテムのView (この場合はTextView)
            // position: クリックされたアイテムのリスト内での位置 (0から始まるインデックス)
            // id: クリックされたアイテムの行ID (通常はpositionと同じか、DBのIDなど)
            val selectedGameTitle = parent.getItemAtPosition(position) as String
            //ObjectからString
            val dialogFragment = GameOptionDialog(selectedGameTitle)
            dialogFragment.show(supportFragmentManager, "GameOptionDialog")
            // ダイアログフラグメントを表示
            // supportFragmentManager: フラグメントを管理するためのオブジェクト
        }
    }
}

