package jp.ac.neec.it.k023c0039.k07_0039_intent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class SevenFragment : Fragment(R.layout.fragment_seven) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // argumentsからデータを受け取るように変更
        val hasMoney = arguments?.getInt("money", 0) ?: 0
        val textViewHasMoney = view.findViewById<TextView>(R.id.has_money)
        textViewHasMoney.text = hasMoney.toString()
        val lvMenu = view.findViewById<ListView>(R.id.lvMenu)
        val menuList: MutableList<MutableMap<String, String>> = mutableListOf()

        val menus = listOf(
            "直火焼き牛カルビ弁当", "銀鮭の塩焼き弁当", "ミートソーススパゲッティ",
            "炙り焼きチキン弁当", "豚ロース生姜焼き弁当", "麻婆豆腐丼",
            "三元豚のロースかつ丼", "鶏そぼろごはん", "明太子おにぎり", "ツナマヨおにぎり"
        )
        val prices = listOf(
            "598円", "550円", "430円",
            "530円", "560円", "470円",
            "598円", "420円", "150円", "140円"
        )

        for (i in menus.indices) {
            val item = mutableMapOf("name" to menus[i], "price" to prices[i])
            menuList.add(item)
        }

        val from = arrayOf("name", "price")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(requireContext(), menuList, android.R.layout.simple_list_item_2, from, to)

        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener(hasMoney)
    }

    private inner class ListItemClickListener(private val hasMoney: Int) : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            val item = parent.getItemAtPosition(position) as MutableMap<String, String>
            val menuName = item["name"]
            val menuPrice = item["price"]
            val confirmFragment = SevenFragmentConfirm()
            val bundle = Bundle()

            bundle.putString("menuName", menuName)
            bundle.putString("menuPrice", menuPrice)
            bundle.putInt("hasMoney", hasMoney)
            confirmFragment.arguments = bundle

            activity?.let {

                val tranzaction = parentFragmentManager.beginTransaction()
                tranzaction.setReorderingAllowed(true)
                val fragmentMainContainer= it.findViewById<View>(R.id.fragment_container_seven)
                if(fragmentMainContainer != null){
                    tranzaction.addToBackStack("Only List")
                    tranzaction.replace(
                        R.id.fragment_container_seven,
                        SevenFragmentConfirm::class.java,
                        bundle
                    )
                }
                else{
                    tranzaction.replace(R.id.fragmentConfirmContainer,SevenFragmentConfirm::class.java, bundle)
                }
                tranzaction.commit()

            }
        }
    }
}