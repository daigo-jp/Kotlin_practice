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

class GameStoreActivity : AppCompatActivity() {
    private var _menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
    private val _from = arrayOf("name", "price")
    private val _to = intArrayOf(R.id.tvMenuNameRow, R.id.tvMenuPriceRow)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven)
        _menuList = createActionList()

        val has_money = intent.getIntExtra("money", 0)
        val textView = findViewById<TextView>(R.id.has_money)
        textView.text = has_money.toString()

        val lvMenu = findViewById<ListView>(R.id.lvMenu)

        val adapter = SimpleAdapter(this, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()
        registerForContextMenu(lvMenu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    // --- コンテキストメニュー（長押し）の処理 ---
    override fun onCreateContextMenu(
        menu: ContextMenu,
        view: View,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, view, menuInfo)
        menuInflater.inflate(R.menu.menu_context_game, menu)
        menu.setHeaderTitle("メニュー")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var returnVal = true
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val listPosition = info.position
        val menu = _menuList[listPosition]

        when (item.itemId) {
            R.id.menuListContextDesc -> {
                val desc = menu["desc"] as String
                Toast.makeText(this, desc, Toast.LENGTH_LONG).show()
            }
            R.id.menuContextOrder -> {
                order(menu)
            }
            else -> {
                returnVal = super.onContextItemSelected(item)
            }
        }
        return returnVal
    }

    // --- オプションメニュー（右上のメニュー）の処理 ---
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnvalue = true
        when (item.itemId) {
            R.id.menu_action -> {
                _menuList = createActionList()
            }
            R.id.menu_rpg -> {
                _menuList = createRpgList()
            }
            R.id.menu_family -> {
                _menuList = createFamilyList()
            }
            android.R.id.home -> {
                finish()
            }
            else -> {
                returnvalue = super.onOptionsItemSelected(item)
            }
        }

        // ここで毎回Adapterを再生成してセットし直す
        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(this, _menuList, R.layout.row, _from, _to)
        lvMenu.adapter = adapter

        return returnvalue
    }

    // --- リスト作成関数 ---
    private fun createActionList(): MutableList<MutableMap<String, Any>> {
        val list: MutableList<MutableMap<String, Any>> = mutableListOf()
        val names = listOf("ELDEN RING", "モンスターハンター：ワールド", "スプラトゥーン3", "ストリートファイター6", "アーマード・コアVI", "サイバーパンク2077", "仁王2", "SEKIRO", "Apex Legends", "Call of Duty")
        val prices = listOf("9240円", "3960円", "6500円", "7990円", "8690円", "8580円", "6380円", "8360円", "0円", "9680円")
        val descs = listOf("広大な世界を冒険する高難易度アクションRPG。", "巨大なモンスターを狩るハンティングアクション。", "インクを撃ち合う4対4のチーム対戦アクション。", "進化を遂げた対戦格闘ゲームの最高峰。", "パーツを組み替えて自分だけのメカで戦うアクション。", "巨大都市ナイトシティを舞台にしたオープンワールドRPG。", "戦国時代末期を舞台にした高難易度アクションRPG。", "孤独な忍びの戦いを描くアクションアドベンチャー。", "3人1組で戦う基本プレイ無料のバトルロイヤル。", "リアルな現代戦がテーマのファーストパーソンシューター。")
        for (i in names.indices) {
            list.add(mutableMapOf("name" to names[i], "price" to prices[i], "desc" to descs[i]))
        }
        return list
    }

    private fun createRpgList(): MutableList<MutableMap<String, Any>> {
        val list: MutableList<MutableMap<String, Any>> = mutableListOf()
        val names = listOf("FINAL FANTASY VII REBIRTH", "ゼルダの伝説 ティアーズ オブ ザ キングダム", "ポケットモンスター スカーレット", "ペルソナ５ ザ・ロイヤル", "ドラゴンクエストXI S", "テイルズ オブ アライズ", "ライザのアトリエ３", "オクトパストラベラーII", "STAR OCEAN THE SECOND STORY R", "グランブルーファンタジー リリンク")
        val prices = listOf("9878円", "7920円", "6500円", "7678円", "5478円", "8778円", "8580円", "7800円", "6578円", "8778円")
        val descs = listOf("FF7リメイクプロジェクトの第2作目。広大なワールドを冒険するRPG。", "空にまで広がった広大な世界を冒険するアクションアドベンチャー。", "オープンワールドで宝探し。新たなポケモンと出会うRPG。", "現代の東京を舞台に、心の怪盗団として活躍する学園RPG。", "勇者の物語を描く、これぞ王道のコマンドバトルRPG。", "美麗なグラフィックと爽快なバトルが魅力のRPG。", "錬金術をテーマにした人気シリーズの完結編。", "ドット絵と3DCGが融合した「HD-2D」で描かれる群像劇RPG。", "1998年の名作を美麗にフルリメイクしたRPG。", "空の世界を舞台にした爽快なアクションRPG。")
        for (i in names.indices) {
            list.add(mutableMapOf("name" to names[i], "price" to prices[i], "desc" to descs[i]))
        }
        return list
    }

    private fun createFamilyList(): MutableList<MutableMap<String, Any>> {
        val list: MutableList<MutableMap<String, Any>> = mutableListOf()
        val names = listOf("あつまれ どうぶつの森", "マリオカート8 デラックス", "Minecraft", "大乱闘スマッシュブラザーズ SPECIAL", "スプラトゥーン3", "スーパーマリオブラザーズ ワンダー", "桃太郎電鉄ワールド", "Nintendo Switch Sports", "Pikmin 4", "星のカービィ ディスカバリー")
        val prices = listOf("6578円", "6578円", "3960円", "7920円", "6500円", "6500円", "6930円", "5478円", "6500円", "6500円")
        val descs = listOf("無人島に移住し、自由気ままなスローライフを楽しむ。", "家族や友達と盛り上がれる定番レースゲーム。", "ブロックで世界を自由に創造するサンドボックスゲーム。", "任天堂のキャラクターが勢ぞろいする対戦アクション。", "インクを撃ち合う4対4のチーム対戦シューター。", "不思議な変化「ワンダー」が待ち受ける2Dマリオの最新作。", "サイコロを振って日本一の社長を目指すボードゲーム。", "体感操作で様々なスポーツが楽しめるパーティーゲーム。", "不思議な生き物ピクミンと協力して冒険するAIアクション。", "カービィ初の3Dアクション。新たな世界を冒険しよう。")
        for (i in names.indices) {
            list.add(mutableMapOf("name" to names[i], "price" to prices[i], "desc" to descs[i]))
        }
        return list
    }

    // --- クリック処理 ---
    private fun order(menu: MutableMap<String, Any>) {
        val menuName = menu["name"] as String
        val menuPrice = menu["price"] as String
        val has_money = intent.getIntExtra("money", 0)

        val intent3Confirm = Intent(this, Game_Confirm::class.java)
        intent3Confirm.putExtra("menuName", menuName)
        intent3Confirm.putExtra("menuPrice", menuPrice)
        intent3Confirm.putExtra("hasMoney", has_money)
        startActivity(intent3Confirm)
    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, Any>
            order(item)
        }
    }
}