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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
                    val game = Game(GlobalScope)
                    Start(m = Modifier.padding(innerPadding), game)




                }
            }
        }
    }
}
@Composable
fun Start(m: Modifier, game:Game){
    val counter by game.state.collectAsState()
    Row {
        Button(
            onClick = {
                game.Play()
            }
        ) {
            Text(text = "開始")
        }
        Text(text = counter.toString(), modifier = m)
    }

Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = "背景圖",
        contentScale = ContentScale.FillBounds,  //縮放符合螢幕寬度
        modifier = Modifier
    )

    Image(
        painter = painterResource(id = R.drawable.seaturtle),
        contentDescription = "海龜",
        modifier = Modifier
            .size(100.dp)
            .offset { IntOffset(1000, y = 200) }
    )
    Image(
        painter = painterResource(id = R.drawable.clownfish),
        contentDescription = "小丑魚",
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(500, y = 100) }
    )
    Image(
        painter = painterResource(id = R.drawable.dolphin),
        contentDescription = "海豚",
        modifier = Modifier
            .size(150.dp)
            .offset { IntOffset(670, y = 390) }
    )
    Image(
        painter = painterResource(id = R.drawable.jellyfish),
        contentDescription = "水母",
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(40, y =450) }
    )
    Image(
        painter = painterResource(id = R.drawable.starfish),
        contentDescription = "海星",
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(1500, y = 70) }
    )
    Image(
        painter = painterResource(id = R.drawable.shell),
        contentDescription = "貝殼",
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(x = 90, y = 790) }
    )
    Image(
        painter = painterResource(id = R.drawable.pufferfish),
        contentDescription = "河豚",
        modifier = Modifier
            .size(80.dp)
            .offset { IntOffset(x = 1300, y = 500) }
    )
}
