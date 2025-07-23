package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class SevenFragmentConfirm : Fragment(R.layout.fragment_seven_confirm) { // 1. レイアウトファイルを指定

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. argumentsからデータを受け取る
        val hasMoney = arguments?.getInt("hasMoney", 0) ?: 0
        val menuName = arguments?.getString("menuName")?: ""
        val menuPrice = arguments?.getString("menuPrice")?: ""

        // 3. viewを使ってUI部品を見つける

        val cleanPriceString = menuPrice?.replace("円", "")
        val intPrice = cleanPriceString?.toIntOrNull()

        if (intPrice != null && menuName != null && menuPrice != null) {
            // 4. canAffordItemにviewを渡すように変更
            canAffordItem(view, hasMoney, intPrice, menuName, menuPrice)
        }
        val btBackButton=view.findViewById<Button>(R.id.btn_back)
        btBackButton.setOnClickListener(BackButtonClickListener())


    }
    // 「戻る」ボタンがクリックされたときの処理を定義する内部クラス
    private inner class BackButtonClickListener : View.OnClickListener {
        // ボタンがクリックされたときに呼ばれるメソッド
        override fun onClick(view: View) {


            // このフラグメントをホストしているActivityがnullでなければ、中の処理を実行
            activity?.let {
                // 通常スクリーン用のコンテナがあるか探して、画面サイズを判断
                val fragmentMainContainer = it.findViewById<View>(R.id.fragment_container_seven)

                // 通常スクリーンかどうかで処理を分岐
                if (fragmentMainContainer != null) {
                    //【通常スクリーンの場合】
                    // バックスタック（履歴）を1つ戻すことで、前のFragment（一覧画面）に戻る。
                    parentFragmentManager.popBackStack()
                } else {
                    //【ラージスクリーンの場合】
                    // ラージスクリーンではバックスタックに追加していないため、popBackStack()は効果がない。
                    // そのため、このFragment（SevenFragmentConfirm）を直接削除するトランザクションを開始する。
                    val transaction = parentFragmentManager.beginTransaction()
                    // Fragmentの置き換え順序を最適化
                    transaction.setReorderingAllowed(true)
                    // このFragmentのインスタンスを削除リストに追加
                    transaction.remove(this@SevenFragmentConfirm)
                    // トランザクションを確定し、Fragmentを画面から削除する
                    transaction.commit()
                }
            }
        }
    }


    private fun canAffordItem(view: View, money: Int, menuPriceInt: Int, menuName: String, menuPriceString: String) {
        val textViewMessage = view.findViewById<TextView>(R.id.seven_message)

        if (money < menuPriceInt) { // 所持金が足りない場合
            textViewMessage.text = getString(R.string.conf_text_false)
        } else { // 所持金が足りている場合
            textViewMessage.text = getString(R.string.conf_text_True) + menuName + menuPriceString

        }
    }
}