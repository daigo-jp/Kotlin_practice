package jp.ac.neec.it.k023c0039.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.neec.it.k023c0039.todoapp.ui.theme.ToDoappTheme
import androidx.compose.foundation.layout.Column // Columnを使うため
import androidx.compose.material3.Button // Buttonを使うため
import androidx.compose.ui.Alignment // Alignment.CenterHorizontallyを使うため
import androidx.compose.foundation.layout.Arrangement // Arrangement.Centerを使うため
import androidx.compose.ui.unit.sp // sp（Scaled Pixels）を使うため
import androidx.compose.ui.unit.dp // dp（Density-independent Pixels）を使うため

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // ここは元のコードのまま
            ToDoappTheme {

                // 変更点：ScaffoldとinnerPaddingの処理を削除し、Columnで置き換える
                Column(
                    // 画面全体の中央に配置
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally, // 横方向の中央揃え
                    verticalArrangement = Arrangement.Center // 縦方向の中央揃え
                ) {
                    // 1. テキストを表示
                    Text(
                        text = "Composeの学習を始めよう！",
                        // spを使うためには、import androidx.compose.ui.unit.sp が必要
                        fontSize = 20.sp,
                        // dpを使うためには、import androidx.compose.ui.unit.dp が必要
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // 2. ボタンを配置
                    Button(
                        onClick = {
                            println("ボタンが押されました！")
                        }
                    ) {
                        // ボタンの中に表示するテキスト
                        Text("クリック！")
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToDoappTheme {
        // ここは元のコードのまま
        Scaffold {
            Text(
                text = "Composeの学習を始めよう！",
                modifier = Modifier.padding(it)
            )
        }
    }
}


