package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.os.Bundle
import android.view.View
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
        val textViewMoney = view.findViewById<TextView>(R.id.has_money_conf)
        textViewMoney.text = hasMoney.toString()

        val cleanPriceString = menuPrice?.replace("円", "")
        val intPrice = cleanPriceString?.toIntOrNull()

        if (intPrice != null && menuName != null && menuPrice != null) {
            // 4. canAffordItemにviewを渡すように変更
            canAffordItem(view, hasMoney, intPrice, menuName, menuPrice)
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