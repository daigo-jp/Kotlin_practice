package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SevenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seven)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // MainActivityからIntentで渡されたデータを取得
        val name = intent.getStringExtra("Name")
        val money = intent.getIntExtra("money", 0)

        val fragment = SevenFragment()

        // Fragmentに渡すためのデータ(Bundle)を用意してセット
        val bundle = Bundle()
        bundle.putString("Name", name)
        bundle.putInt("money", money)
        fragment.arguments = bundle

        // FragmentManagerを使い、レイアウト内の指定した場所にFragmentを配置
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_seven, fragment)
            .commit()
    }
    override fun onSupportNavigateUp(): Boolean {
        // まず、Fragmentの履歴を戻せるか試す
        if (supportFragmentManager.popBackStackImmediate()) {
            // 戻れた場合は、ここで処理を終了
            return true
        }

        // 戻るFragmentがなければ、Activityを終了する
        finish()
        return true
    }
}