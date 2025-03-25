package com.example.myapplication

import android.content.Context
import android.net.Uri
import android.support.v4.media.MediaBrowserCompat
import android.widget.VideoView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun PicDetail(navController: NavController, uri: Uri, context: Context , onDeleteImage: (Uri) -> Unit ) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFF6FAEB),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(text = "Delete",
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(5.dp)
                            .clickable {
                                onDeleteImage(uri)
                                navController.navigateUp()
                            }
                    )
                }
            }
        },
        bottomBar = { },
        content = {innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
                contentAlignment = Alignment.Center) {
                if (uri.toString().endsWith(".mp4") || isVideoFile(context, uri)) {
                    VideoPlayer(uri = uri)
                } else {
                    AsyncImage(
                        model = uri,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    )



}

@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) } // 初始化为false，因为一开始并未播放
    var exoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }

    DisposableEffect(uri) {
        val player = ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = isPlaying
            addListener(object : Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    isPlaying = playbackState == Player.STATE_READY && playWhenReady
                }
            })
        }
        exoPlayer = player

        onDispose {
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
        .clickable {
            exoPlayer?.let { player ->
                if (player.isPlaying) {
                    player.pause()
                } else {
                    player.play()
                }
                // 不需要手动设置isPlaying，因为它会由ExoPlayer的监听器自动更新
            }
        },
        contentAlignment = Alignment.Center
    ) {
        AndroidView(factory = { ctx ->
            StyledPlayerView(ctx).apply {
                player = exoPlayer
            }
        }, update = { playerView ->
            playerView.player = exoPlayer
        })
    }
}
