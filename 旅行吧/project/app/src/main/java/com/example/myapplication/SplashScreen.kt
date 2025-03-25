package com.example.myapplication

import android.content.Intent
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenShow {
                // Navigate to MainActivity after splash screen
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

@Composable
fun SplashScreenShow(onTimeout: () -> Unit) {
    var visible by remember { mutableStateOf(true) }
    val density = LocalDensity.current
    val screenHeight = remember { density.density * 800 } // 设备屏幕高度，可以通过具体方法获取

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            // Enter from down
            initialOffsetY = { fullHeight -> fullHeight },
            animationSpec = tween(durationMillis = 1000)
        ),
//        exit = slideOutVertically(
//            targetOffsetY = { fullHeight -> -fullHeight },
//            animationSpec = tween(durationMillis = 1000)
//        )
        exit = fadeOut(
            // Fade out during exit
            animationSpec = tween(durationMillis = 500)
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.startpic),
            contentDescription = "Splash Image",
            //modifier = Modifier.fillMaxSize()
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
        )
    }

    LaunchedEffect(key1 = true) {
        delay(2000) // Stay on the screen for 3 seconds
        visible = false
        //delay(300) // Wait for the exit animation to complete
        onTimeout()
    }
}
