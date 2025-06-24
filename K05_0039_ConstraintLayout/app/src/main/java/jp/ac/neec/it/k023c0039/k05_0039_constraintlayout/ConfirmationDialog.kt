package jp.ac.neec.it.k023c0039.k05_0039_constraintlayout

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

/**
 * 確認ダイアログを表示するためのDialogFragment。
 */
class ConfirmationDialog(private val message: String) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("入力チェック確認")
            builder.setMessage(message)
            val listener = DialogButtonClickListener()
            builder.setPositiveButton("OK", listener)
            builder.setNegativeButton("キャンセル", listener)
            builder.create()
        }
        return dialog ?: throw IllegalStateException("Activity cannot be null")
    }

    private inner class DialogButtonClickListener : DialogInterface.OnClickListener {
        override fun onClick(dialog: DialogInterface, which: Int) {
            val msg = when (which) {
                DialogInterface.BUTTON_POSITIVE -> "OKしました"
                DialogInterface.BUTTON_NEGATIVE -> "キャンセルしました"
                else -> ""
            }
            if (msg.isNotEmpty()) {
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
