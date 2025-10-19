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
import androidx.compose.ui.tooling.preview.Preview
import jp.ac.neec.it.k023c0039.todoapp.ui.theme.ToDoappTheme
import androidx.compose.foundation.layout.Column // Columnを使うため
import androidx.compose.material3.Button // Buttonを使うため
import androidx.compose.ui.Alignment // Alignment.CenterHorizontallyを使うため
import androidx.compose.foundation.layout.Arrangement // Arrangement.Centerを使うため
import androidx.compose.ui.unit.sp // sp（Scaled Pixels）を使うため
import androidx.compose.ui.unit.dp // dp（Density-independent Pixels）を使うため
import androidx.compose.material3.Scaffold // Scaffoldを使うため
import androidx.compose.material3.TopAppBar // TopAppBarを使うため
import androidx.compose.material3.ExperimentalMaterial3Api // TopAppBarを使うために必要
import androidx.compose.foundation.layout.Row // Rowを使うため
import androidx.compose.foundation.layout.fillMaxWidth // fillMaxWidthを使うため
import androidx.compose.foundation.layout.padding // paddingを使うため
import androidx.compose.foundation.layout.Column // Columnを使うため
import androidx.compose.ui.Modifier // Modifierを使うため
import androidx.compose.ui.unit.dp // dpを使うため
import androidx.compose.material3.TopAppBar // TopAppBarを使うため
import androidx.compose.material3.Text // Textを使うため
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // MainActivity.kt の setContent ブロック内

        setContent {
            // 既存のテーマ
            ToDoappTheme {
                // Scaffoldを導入
                Scaffold(
                    // 1. トップバーの設定
                    topBar = {
                        // TopAppBar（Android Developersで推奨されるトップバー）
                        TopAppBar(
                            title = {
                                Text("My Compose ToDo") // アプリ名
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    // 2. メインコンテンツの配置（次のステップでTaskListを配置）
                    // innerPaddingを使って、TopAppBarに隠れないように余白を確保します
                    MainContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}
// MainActivity.kt の class MainActivity { ... } の外側

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    // Columnを使って、要素を縦に（上から順に）並べる
    Column(modifier = modifier.fillMaxSize()) {

        // 1. タスク入力エリア（仮置き）
        TaskInputArea()

        // 2. タスクリスト表示エリア（仮置き）
        TaskList()
    }
}

// タスク入力エリアの仮コンポーザブル
@Composable
fun TaskInputArea() {
    // Row（横並び）を使って、入力フィールドとボタンを並べることを想定
    Row(
        modifier = Modifier
            .fillMaxWidth() // 横幅いっぱいに広げる
            .padding(16.dp) // 外側に余白
    ) {
        Text(text = "新しいタスクを入力...", modifier = Modifier.weight(1f)) // 重み(weight)で領域を広げる
        // ここに後で TextField と Button が入ります
    }
}

// タスクリスト表示エリアの仮コンポーザブル
@Composable
fun TaskList() {
    // LazyColumnは、大量のアイテムを効率的に表示するためのリストコンポーザブル
    // この中に実際のタスクアイテムが縦に並びます
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text("--- 実際のタスクリスト ---")
        // ここに後でタスクアイテム（TaskItem）が繰り返し表示されます
    }
}

@Preview(showBackground = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoAppLayoutPreview() {
    ToDoappTheme {
        // MainActivityのsetContentブロックとほぼ同じ内容を定義
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("My Compose ToDo")
                    }
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            MainContent(Modifier.padding(innerPadding))
        }
    }
}

