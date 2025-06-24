package jp.ac.neec.it.k023c0039.k03_0039_event

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btclick=findViewById<Button>(R.id.btClick)
        val listener=HelloListener()
        btclick.setOnClickListener(listener)
        val btClear=findViewById<Button>(R.id.btClear)
        btClear.setOnClickListener(listener)


    }
    private inner class HelloListener: View.OnClickListener{
        override fun onClick(view: View) {
            val input1=findViewById<EditText>(R.id.etName1)
            val inputText1=input1.text.toString()
            val input2=findViewById<EditText>(R.id.etName2)
            val inputText2=input2.text.toString()
            val output =findViewById<TextView>(R.id.tvOutput)
            if(view.id==R.id.btClick){
                if (inputText1.isBlank()&&inputText2.isBlank()) {
                    output.text=getString(R.string.enp_mg)
                }
                else if(!inputText1.isBlank()&&inputText2.isBlank()){
                    output.text=inputText1+getString(R.string.msg2_enp)
                }
                else if(inputText1.isBlank()&&!inputText2.isBlank()){
                    output.text=inputText2+getString(R.string.msg_hima)
                }
                else{
                    output.text=inputText1+getString(R.string.msg2_enp)+inputText2+getString(R.string.msg1_enp)
                }
            }
            else if(view.id==R.id.btClear){
                input1.setText("")
                input2.setText("")
                output.text=""
            }
        }

    }
}