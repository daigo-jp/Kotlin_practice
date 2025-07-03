package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SevenActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    private val _from = arrayOf("name", "price")//どの列のデータを使うか
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)//row.xmlのどのViewにデータをセットするか
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven)
        _menuList =createFoodList()
        val name = intent.getStringExtra("Name")
        val has_money = intent.getIntExtra("money", 0) // 第2引数はデフォルト値です
        val textView = findViewById<TextView>(R.id.has_money)
        textView.text = has_money.toString()
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(
            this@SevenActivity,
            _menuList,
            R.layout.row,
            _from,
            _to
        )
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
        registerForContextMenu(lvMenu)//項目を長押しした時にコンテキストメニューが表示されるように設定
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,//空っぽのメニューオブジェクト
        view: View,//長押しされたUI
        menuInfo: ContextMenu.ContextMenuInfo//何番目の項目が押されたかのpositionなどを取得するための引数
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)
        menuInflater.inflate(R.menu.menu_context_seven, menu)//「説明を表示」などを生成して、空っぽだったmenuオブジェクトに追加
        menu.setHeaderTitle(R.string.menu_list_options_header)//タイトルをつける
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true//基本的にクリック処理に成功したことを示すtrueを返す前提で動作します」という初期設定です。

        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo   //  大雑把なmenuInfoを、AdapterView.AdapterContextMenuInfo型に変換
        val listPosition = info.position  // 詳細情報から「何番目の項目か」という位置(position)を取り出す
        val menu = _menuList[listPosition]// その位置を元に、データリストから該当する1行分のデータを取得
        when (item.itemId) {
            R.id.menuListContextDesc -> {
                // 「説明を表示」の場合、データから"desc"を取り出して表示
                val desc = menu["desc"] as String
                Toast.makeText(this@SevenActivity, desc, Toast.LENGTH_LONG).show()
            }

            R.id.menuContextOrder -> {
                //「注文する」の場合、データをorder関数に渡す
                order(menu)
            }

            else -> {
                returnVal = super.onContextItemSelected(item)
            }
        }
        return returnVal
    }

    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as String
        val has_money = intent.getIntExtra("money", 0)

        val intent3Confirm = Intent(this@SevenActivity, Seven_Confirm::class.java)
        intent3Confirm.putExtra("menuName", menuName)
        intent3Confirm.putExtra("menuPrice", menuPrice)
        intent3Confirm.putExtra("hasMoney", has_money)
        startActivity(intent3Confirm)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_seven, menu)//「食品」や「日用品」といった項目が、実際のボタンとしてmenuオブジェクトの中に作られます
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnvalue = true
        when (item.itemId) {
            R.id.menu_food -> {
                _menuList = createFoodList()
            }

            R.id.menu_daily -> {
                _menuList = createDailyList()
            }

            R.id.menu_service -> {
                _menuList = createServiceList()
            }
            android.R.id.home -> {
                finish()
            }

            else -> {
                returnvalue = super.onOptionsItemSelected(item)
            }
        }
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(
            this@SevenActivity,
            _menuList,//商品名や価格が入ったMapのリストが渡されます。
            R.layout.row,//1行分を表示するためのレイアウトファイル
            _from,//_menuListの各行のデータから、どのキーの値を取り出すか
            _to//row.xmlのどのViewにデータをセットするか
        )
        lvMenu.adapter = adapter
        return returnvalue
    }


    private fun createFoodList(): MutableList<MutableMap<String, Any>> {
        val foodList: MutableList<MutableMap<String, Any>> = mutableListOf()


        val menus: MutableList<String> = mutableListOf(
            "直火焼き牛カルビ弁当", "銀鮭の塩焼き弁当", "ミートソーススパゲッティ",
            "炙り焼きチキン弁当", "豚ロース生姜焼き弁当", "麻婆豆腐丼",
            "三元豚のロースかつ丼", "鶏そぼろごはん", "明太子おにぎり", "ツナマヨおにぎり"
        )
        val prices: MutableList<String> = mutableListOf(
            "598円", "550円", "430円",
            "530円", "560円", "470円",
            "598円", "420円", "150円", "140円"
        )
        val descs: MutableList<String> = mutableListOf(
            "直火で香ばしく焼き上げた牛カルビを、特製の甘辛いタレで仕上げた満足感のあるお弁当です。",
            "脂の乗った銀鮭を丁寧に塩焼きにし、ご飯がすすむ定番の和風弁当です。",
            "じっくり煮込んだ挽き肉の旨味が溶け込んだ、コクのある本格的なミートソーススパゲッティです。",
            "香ばしく炙り焼きにした、ジューシーなチキンが主役。食べ応え十分なお弁当です。",
            "柔らかい豚ロースを、風味豊かな特製生姜ダレで絡めました。ご飯との相性抜群です。",
            "本格四川風の、花椒（ホアジャオ）が効いた痺れる辛さがクセになる麻婆豆腐丼です。",
            "柔らかくジューシーな三元豚のロースかつを、こだわりのだしと、とろとろの卵でとじました。",
            "甘辛く味付けした鶏そぼろと、彩りの良い炒り卵をのせた、優しい味わいのご飯です。",
            "ピリッとした辛さとプチプチとした食感が楽しめる、人気の博多明太子をたっぷり使用しました。",
            "ツナの旨味と濃厚なマヨネーズを合わせた、子供から大人まで大人気の定番おにぎりです。"
        )

        for (i in menus.indices) {//iには1,2,3...と順番に0から始まる番号が入ります。
            var menu = mutableMapOf<String, Any>(
                "name" to menus[i],
                "price" to prices[i],
                "desc" to descs[i]
            )
            foodList.add(menu)
        }


        return foodList
    }

    private fun createDailyList(): MutableList<MutableMap<String, Any>> {
        val dailyList: MutableList<MutableMap<String, Any>> = mutableListOf()

        val menus: MutableList<String> = mutableListOf(
            "ビニール傘 65cm",
            "ポケットティッシュ 4個パック",
            "単3形アルカリ乾電池 4本パック",
            "不織布マスク 7枚入り",
            "トラベル用歯ブラシセット",
            "洗濯用洗剤 ワンパック",
            "使い捨てカミソリ 2本入",
            "瞬間接着剤",
            "スマートフォン充電ケーブル (Type-C)",
            "A6サイズノート (無地)"
        )
        val prices: MutableList<String> = mutableListOf(
            "638円", "140円", "473円",
            "327円", "297円", "88円",
            "220円", "330円", "1280円", "154円"
        )
        val descs: MutableList<String> = mutableListOf(
            "急な雨でも安心。大きめサイズで軽くて丈夫なジャンプ式のビニール傘です。",
            "外出時の必需品。柔らかく肌に優しい、水に流せるポケットティッシュの4個セット。",
            "リモコンや時計など、様々な機器に使えるパワフルなアルカリ乾電池。",
            "個包装で持ち運びに便利。3層フィルターで花粉やウイルス飛沫をカットします。",
            "出張や旅行に便利な、歯ブラシとミニ歯磨き粉の携帯用セットです。",
            "旅行やコインランドリーでの利用に便利な、1回使い切りの液体洗剤です。",
            "2枚刃で肌に優しく、スムーズな剃り心地。携帯に便利なキャップ付きです。",
            "プラスチックや金属、陶器などに使える強力な接着剤。細かい作業に便利なノズル付き。",
            "スマートフォンの充電とデータ転送に対応したUSB Type-Cケーブルです。長さ1m。",
            "ポケットにも入るコンパクトなA6サイズのノート。アイデアの記録やメモに最適です。"

        )

        for (i in menus.indices) {
            var menu = mutableMapOf<String, Any>(
                "name" to menus[i],
                "price" to prices[i],
                "desc" to descs[i]
            )
            dailyList.add(menu)
        }
        return dailyList
    }

    private fun createServiceList(): MutableList<MutableMap<String, Any>> {
        val serviceList: MutableList<MutableMap<String, Any>> = mutableListOf()
        val menus: MutableList<String> = mutableListOf(
            "セブンカフェ レギュラーコーヒー(R)",
            "マルチコピー機",
            "セブン銀行ATM",
            "宅配便受付",
            "無料Wi-Fiサービス",
            "公共料金・各種料金収納代行",
            "セブンミール (食事宅配サービス)",
            "チケット発券サービス",
            "シェアサイクル",
            "モバイルバッテリーレンタル"

        )
        val prices: MutableList<String> = mutableListOf(
            "110円", "10円", "0円",
            "0円", "0円", "0円",
            "0円", "0円", "165円", "330円"
        )
        val descs: MutableList<String> = mutableListOf(
            "挽きたての豆を一杯ずつドリップ。香り高い本格的なコーヒーを手軽にお楽しみいただけます。",
            "コピー、写真プリントなど多彩な機能が利用できます（料金はサービスにより異なります）。",
            "現金のお引き出し、お預け入れなどが24時間利用可能です（手数料は金融機関により異なります）。",
            "ヤマト運輸の宅急便やメルカリの発送手続きが可能です（料金はサイズや宛先により変動します）。",
            "店内で利用可能な無料のWi-Fiサービスです。簡単な登録ですぐにインターネットに接続できます。",
            "電気、ガス、水道などの公共料金や通信販売などの代金をレジで24時間お支払いいただけます。",
            "管理栄養士が監修したお弁当やおかずを、ご自宅や職場に毎日お届けするサービスです。",
            "コンサート、スポーツ、映画などの各種チケットをマルチコピー機で購入・発券できます。",
            "スマートフォンのアプリで簡単に借りられる自転車シェアリングサービス。短時間の移動に便利です。",
            "スマートフォンの急な充電切れに。専用スタンドでどこでも借りられて、どこでも返せます。"

        )

        for (i in menus.indices) {
            var menu = mutableMapOf<String, Any>(
                "name" to menus[i],
                "price" to prices[i],
                "desc" to descs[i]
            )
            serviceList.add(menu)
        }
        return serviceList
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)


        }
    }
}