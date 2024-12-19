package com.example.examapp

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
import com.example.examapp.ui.theme.ExamAPPTheme
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import kotlinx.coroutines.GlobalScope
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource




class MainActivity : ComponentActivity() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    var isGameStarted by remember { mutableStateOf(false) }
                    var selectedFish by remember { mutableStateOf<String?>(null) }

                    if (!isGameStarted) {
                        // 起始畫面
                        FirstScreen(onStartClicked = { isGameStarted = true })

                    } else {

                        if (selectedFish == null) {
                            StartScreen(
                                onFishClicked = { fishName -> selectedFish = fishName },
                                onExitClicked = { finish() } // 傳遞退出程式的回調
                            )
                        } else {
                            ShowFishBackground(
                                fishName = selectedFish!!,
                                onBackClicked = { selectedFish = null },
                                onVoiceClicked = { selectedFish?.let { playVoice(it) } }
                            )
                        }
                    }
                }
            }
        }
    }

private fun playVoice(fishName: String) {
    // 停止舊的音檔
    mediaPlayer?.stop()
    mediaPlayer?.release()

    // 根據魚名稱播放對應音檔
    val voiceRes = when (fishName) {
        "jellyfish" -> R.raw.jellyfish
        "seaturtle" -> R.raw.seaturtle
        "clownfish" -> R.raw.clownfish
        "starfish" -> R.raw.starfish
        "shell" -> R.raw.shell
        "pufferfish" -> R.raw.pufferfish
        "dolphin" -> R.raw.dolphin
        else -> null
    }

    if (voiceRes != null) {
        mediaPlayer = MediaPlayer.create(this, voiceRes)
        mediaPlayer?.start()
    }
}

override fun onDestroy() {
    super.onDestroy()
    mediaPlayer?.release()
}
}


@Composable
fun StartScreen(onFishClicked: (String) -> Unit,onExitClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "背景圖",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.jellyfish),
            contentDescription = "水母",
            modifier = Modifier
                .size(80.dp)
                .offset { IntOffset(40, 450) }
                .clickable { onFishClicked("jellyfish") }
        )
        Image(
            painter = painterResource(id = R.drawable.seaturtle),
            contentDescription = "海龜",
            modifier = Modifier
                .size(100.dp)
                .offset { IntOffset(1000, 200) }
                .clickable { onFishClicked("seaturtle") }
        )
        Image(
            painter = painterResource(id = R.drawable.clownfish),
            contentDescription = "小丑魚",
            modifier = Modifier
                .size(80.dp)
                .offset { IntOffset(500, 100) }
                .clickable { onFishClicked("clownfish") }
        )
        Image(
            painter = painterResource(id = R.drawable.starfish),
            contentDescription = "海星",
            modifier = Modifier
                .size(80.dp)
                .offset { IntOffset(1500, y = 70) }
                .clickable { onFishClicked("starfish") }
        )
        Image(
            painter = painterResource(id = R.drawable.shell),
            contentDescription = "貝殼",
            modifier = Modifier
                .size(80.dp)
                .offset { IntOffset(x = 90, y = 790) }
                .clickable { onFishClicked("shell") }
        )
        Image(
            painter = painterResource(id = R.drawable.pufferfish),
            contentDescription = "河豚",
            modifier = Modifier
                .size(80.dp)
                .offset { IntOffset(x = 1300, y = 500) }
                .clickable { onFishClicked("pufferfish") }
        )
        Image(
            painter = painterResource(id = R.drawable.dolphin),
            contentDescription = "海豚",
            modifier = Modifier
                .size(150.dp)
                .offset { IntOffset(670, y = 390) }
                .clickable { onFishClicked("dolphin") }
        )
        Image(
            painter = painterResource(id = R.drawable.exit),
            contentDescription = "結束按鈕",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .clickable { onExitClicked() }
        )

    }
}



@Composable
fun ShowFishBackground(fishName: String, onBackClicked: () -> Unit,onVoiceClicked: () -> Unit) {
    val backgroundRes = when (fishName) {
        "jellyfish" -> R.drawable.jellyfishbackground
        "seaturtle" -> R.drawable.seaturtlebackground
        "clownfish" -> R.drawable.cfishbackground
        "starfish" -> R.drawable.starbackground
        "shell" -> R.drawable.shellbackground
        "pufferfish" -> R.drawable.pufferfishbackground
        "dolphin" -> R.drawable.dolphinbackground
        else -> R.drawable.background
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "魚背景圖",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.backbutton),
            contentDescription = "返回按鈕",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable { onBackClicked() }
        )
        Image(
            painter = painterResource(id = R.drawable.voice),
            contentDescription = "語音按鈕",
            modifier = Modifier
                .size(80.dp)
                .offset(x = 300.dp, y = 200.dp)
                .clickable { onVoiceClicked() }

        )
    }
}
@Composable
fun FirstScreen(onStartClicked: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.first),
            contentDescription = "起始畫面背景",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.start),
            contentDescription = "開始按鈕",
            modifier = Modifier
                .size(150.dp)
                .offset { IntOffset(750, 600) }
                .clickable { onStartClicked() }
        )
    }
}


