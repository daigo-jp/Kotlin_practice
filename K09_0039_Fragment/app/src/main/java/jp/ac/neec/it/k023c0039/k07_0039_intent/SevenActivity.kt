package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SevenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ここで activity_seven.xml またはラージスクリーン用のXMLが読み込まれる
        setContentView(R.layout.activity_seven)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("Name")
        val money = intent.getIntExtra("money", 0)
        val bundle = Bundle()
        bundle.putString("Name", name)
        bundle.putInt("money", money)


        // ラージスクリーン用のコンテナがあるかチェック
        val listContainer = findViewById<View>(R.id.fragmentListContainer)

        // ラージスクリーンか通常スクリーンかで、対象となるFragmentのIDを決定する
        val fragmentId = if (listContainer != null) {
            // ラージスクリーンの場合
            R.id.fragmentListContainer
        } else {
            // 通常スクリーンの場合
            R.id.fragment_container_seven
        }

        // 決定したIDを使って、XMLが自動生成したFragmentのインスタンスを見つける
        val fragment = supportFragmentManager.findFragmentById(fragmentId)

        // 見つけたFragmentにデータ(Bundle)を渡す
        fragment?.arguments = bundle
    }


    override fun onSupportNavigateUp(): Boolean {
        // まず、Fragmentのバックスタック（履歴）を戻せるか試す
        if (supportFragmentManager.popBackStackImmediate()) {
            return true
        }
        // 戻るFragmentがなければ、このActivity自体を終了する
        finish()
        return true
    }
}