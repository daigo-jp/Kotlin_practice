package jp.ac.neec.it.k023c0039.k10_0039_db

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // データベースヘルパー
    private lateinit var _helper: DatabaseHelper
    // ListViewのインスタンスを保持する変数
    private lateinit var lvItem: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // データベースヘルパーを初期化
        _helper = DatabaseHelper(this@MainActivity)
        // レイアウトからListViewのインスタンスを取得
        lvItem = findViewById(R.id.lvItem)

        // 最初に画面が表示されたときにリストを読み込む
        refreshListView()
    }

    override fun onResume() {
        super.onResume()
        // この画面に戻ってくるたびにリストを更新する
        refreshListView()
    }

    private fun refreshListView() {
        val db = _helper.readableDatabase
        val dataList = mutableListOf<Map<String, Any>>()

        try {
            val cursor = db.query(
                "ItemList", // テーブル名
                arrayOf("_id", "name", "price"), // 取得するカラム
                null, null, null, null, null // 条件などはなし
            )

            // cursor.use を使うと自動的にcloseされるので安全
            cursor.use {
                // Cursorからデータを1行ずつ読み取り、dataListに追加
                while (it.moveToNext()) {
                    // カラムのインデックスを取得
                    val nameIndex = it.getColumnIndex("name")
                    val priceIndex = it.getColumnIndex("price")

                    // データを取得
                    val name = it.getString(nameIndex)
                    val price = it.getInt(priceIndex)

                    // 1行分のデータをMapとして作成
                    val item = mapOf("name" to name, "price" to "価格: $price 円")
                    dataList.add(item)
                }
            }
        } finally {
            db.close()
        }

        // SimpleAdapterを生成
        val adapter = SimpleAdapter(
            this,
            dataList, // 表示するデータ
            R.layout.list_item, // 各行のレイアウト
            arrayOf("name", "price"), // どのデータを
            intArrayOf(R.id.tvItemName, R.id.tvItemPrice) // どのビューに表示するか
        )

        // ListViewにアダプターをセット
        lvItem.adapter = adapter
    }


    fun ConfigButtonClick(view: View) {
        val intent = Intent(this@MainActivity, Config::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        _helper.close()
        super.onDestroy()
    }
}