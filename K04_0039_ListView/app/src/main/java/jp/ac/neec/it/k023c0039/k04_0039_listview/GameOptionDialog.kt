package jp.ac.neec.it.k023c0039.k04_0039_listview

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class GameOptionDialog(private val gameTitleToDisplay: String ) : DialogFragment() {



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            //activityがnullでない場合｛｝でdialogが初期化される
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.dialog_title)
            val displayMessage = "${gameTitleToDisplay}${getString(R.string.dialog_ok_toast1)}"
            builder.setMessage(displayMessage)
            val buttonClickListener = DialogButtonClickListener()
            builder.setPositiveButton(R.string.dialog_btn_ok, buttonClickListener)
            builder.setNegativeButton(R.string.dialog_btn_ng, buttonClickListener)
            builder.setNeutralButton(R.string.dialog_btn_nu, buttonClickListener)
            builder.create()
        }
        return dialog ?: throw IllegalStateException("アクティビティがnullです")
    }

    private inner class DialogButtonClickListener() : DialogInterface.OnClickListener {
        //DialogInterface.OnClickListener というインターフェースを実装
        override fun onClick(dialog: DialogInterface, which: Int) {
            var msg = ""

            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    val titleToast = gameTitleToDisplay
                    msg = getString(R.string.dialog_ok_toast)+titleToast+getString(R.string.dialog_play)
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                    msg = getString(R.string.dialog_ng_toast)
                }
                DialogInterface.BUTTON_NEUTRAL -> {
                    msg = getString(R.string.dialog_nu_toast)
                }
            }
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        }
    }
}