package jp.ac.neec.it.k023c0039.k05_0039_constraintlayout

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    /**
     * ユーザーの入力情報を保持するためのデータクラス。
     */
    data class UserInfo(
        val emptyFieldCount: Int,
        val emailAddress: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // クリックリスナーを設定
        setupClickListeners()
    }

    /**
     * 各ボタンのクリックリスナーを設定します。
     */
    private fun setupClickListeners() {
        // 確認ボタンのリスナー
        findViewById<Button>(R.id.btConfrim).setOnClickListener {
            val userInfo = createUserInfoFromInput()
            onConfirmButtonClick(userInfo.emptyFieldCount)
        }

        findViewById<Button>(R.id.btSend).setOnClickListener {
            val userInfo = createUserInfoFromInput()
            onSendButtonClick(userInfo.emailAddress)
        }


        findViewById<Button>(R.id.btClear).setOnClickListener {
            onClearButtonClick()
        }
    }

    /**。
     * @return UserInfo ユーザー情報のオブジェクト
     */
    private fun createUserInfoFromInput(): UserInfo {
        var count = 0
            val name = findViewById<EditText>(R.id.etName).text.toString()
        if (name.isBlank()) count++
        val name_as = findViewById<EditText>(R.id.etName_as).text.toString()
        if (name_as.isBlank()) count++
        val email = findViewById<EditText>(R.id.etMail).text.toString()
        if (email.isBlank()) count++
        val tel = findViewById<EditText>(R.id.etTel).text.toString()
        if (tel.isBlank()) count++
        val address = findViewById<EditText>(R.id.etAddress).text.toString()
        if (address.isBlank()) count++
        val year = findViewById<EditText>(R.id.etYear).text.toString()
        if (year.isBlank()) count++
        val month = findViewById<EditText>(R.id.etMonth).text.toString()
        if (month.isBlank()) count++
        val day = findViewById<EditText>(R.id.etDay).text.toString()
        if (day.isBlank()) count++
        val message = findViewById<EditText>(R.id.etMessage).text.toString()
        if (message.isBlank()) count++

        // UserInfoオブジェクトを生成して返す
        return UserInfo(emptyFieldCount = count, emailAddress = email)
    }

    /**
     * 確認ボタンがクリックされたときの処理。
     */
    private fun onConfirmButtonClick(emptyFieldCount: Int) {
        val dialogMessage = if (emptyFieldCount > 0) {
            "入力漏れが${emptyFieldCount}個あります"
        } else {
            "そのままメールを送信してください"
        }

        val dialog = ConfirmationDialog(dialogMessage)
        dialog.show(supportFragmentManager, "ConfirmationDialog")
    }

    /**
     * 送信ボタンがクリックされたときの処理。
     */
    private fun onSendButtonClick(emailAddress: String) {
        val message = if (emailAddress.isNotBlank()) {
            "$emailAddress に送信しました"
        } else {
            "E-Mailに送信しました"
        }
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()

        if (emailAddress.isNotBlank()) {
            clearAllFields()
        }
    }

    /**
     * クリアボタンがクリックされたときの処理。
     */
    private fun onClearButtonClick() {
        clearAllFields()
        Toast.makeText(applicationContext, "クリアしました", Toast.LENGTH_SHORT).show()
    }


    /**
     * すべての入力フィールドをクリアします。
     */
    private fun clearAllFields() {
        findViewById<EditText>(R.id.etName).setText("")
        findViewById<EditText>(R.id.etName_as).setText("")
        findViewById<EditText>(R.id.etMail).setText("")
        findViewById<EditText>(R.id.etTel).setText("")
        findViewById<EditText>(R.id.etAddress).setText("")
        findViewById<EditText>(R.id.etYear).setText("")
        findViewById<EditText>(R.id.etMonth).setText("")
        findViewById<EditText>(R.id.etDay).setText("")
        findViewById<EditText>(R.id.etMessage).setText("")
        findViewById<RadioGroup>(R.id.SexGroup).clearCheck()
    }
}