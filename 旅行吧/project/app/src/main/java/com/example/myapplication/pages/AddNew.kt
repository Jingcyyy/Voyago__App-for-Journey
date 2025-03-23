package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.media.ThumbnailUtils
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AssistChipDefaults.Height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import java.io.File

@Composable
fun AddNew(
    navController: NavController,
    imageUris: List<Uri>,
    onselectedImage: () -> Unit,
    onselectedVideo: () -> Unit,
    onImageClick: (Uri) -> Unit,
    onSave: (List<Uri>, String, String) -> Unit,
    ini: () -> Unit
) {
    val context = LocalContext.current
    var textFieldTextHead by rememberSaveable { mutableStateOf("") }
    var textFieldTextBody by rememberSaveable { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFF6FAEB),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
            ) {
                Row {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = {
                        onSave(imageUris, textFieldTextHead, textFieldTextBody)
                        ini()
                        textFieldTextHead = ""
                        textFieldTextBody = ""
                        navController.navigateUp()
                    }) {
                        Icon(
                            painterResource(id = R.drawable.right),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                IconButton(
                    onClick = onselectedImage,
                    modifier = Modifier.weight(1f) // 可选的权重
                ) {
                    Icon(
                        painterResource(id = R.drawable.pic),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(4.dp)
                            .wrapContentSize(Alignment.TopStart)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = onselectedVideo,
                    modifier = Modifier
                        .weight(1f) // 可选的权重
                ) {
                    Icon(
                        painterResource(id = R.drawable.video),
                        contentDescription = "按钮2"
                    )
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                PicLazyRow(imageUris, onselectedImage, onImageClick, context)

                Divider(
                    color = Color(0xFFCFCFCF),
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = textFieldTextHead,
                    onValueChange = { newValue ->
                        textFieldTextHead = newValue
                    },
                    placeholder = {
                        Text(
                            "请输入标题",
                            fontSize = 20.sp,
                            letterSpacing = 1.sp,
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 60.dp)
                        .padding(horizontal = 5.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    maxLines = Int.MAX_VALUE,
                    textStyle = TextStyle(letterSpacing = 1.sp, fontSize = 20.sp)
                )

                Divider(
                    color = Color(0xFF64782F),
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                TextField(
                    value = textFieldTextBody,
                    onValueChange = { newValue ->
                        textFieldTextBody = newValue
                    },
                    placeholder = {
                        Text(
                            "请输入文字",
                            fontSize = 16.sp,
                            letterSpacing = 1.sp,
                            color = Color.Gray
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 400.dp)
                        .padding(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    maxLines = Int.MAX_VALUE,
                    textStyle = TextStyle(letterSpacing = 1.sp, fontSize = 16.sp)
                )
            }
        }
    )

}

@Composable
fun PicLazyRow(
    imageUri: List<Uri>,
    onSelectImages: () -> Unit,
    onImageClick: (Uri) -> Unit,
    context: Context
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp) // 设置固定高度
            .background(Color.White)
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        items(imageUri) { image ->

            println("${image}")
            Box(
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { onImageClick(image) }
            ) {
                if (image.toString().endsWith(".mp4") or isVideoFile(context, image)) {
                    VideoThumbnail(uri = image, context)
                } else {
                    AsyncImage(
                        model = image,
                        contentDescription = null,
                        modifier = Modifier
                            .heightIn(max = 130.dp), // 设置图片大小,
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
        item {
            Box(
                modifier = Modifier
                    //.align(Alignment.CenterVertically)
                    .height(110.dp)
                    .width(110.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFCFCFCF))
                    .clickable {
                        onSelectImages()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "添加图片",
                    letterSpacing = 1.sp,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

fun isVideoFile(context: Context, uri: Uri): Boolean {
    val contentResolver = context.contentResolver
    val mimeType = contentResolver.getType(uri)
    return mimeType?.startsWith("video") == true
}

@Composable
fun VideoThumbnail(uri: Uri, context: Context) {
    Box(
        modifier = Modifier
            .heightIn(max = 130.dp)
            .width(130.dp)
    ) {
        var thumbnail by remember { mutableStateOf<Bitmap?>(null) }

        LaunchedEffect(uri) {
            thumbnail = getVideoThumbnail(context, uri)
        }

        thumbnail?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Icon(
            painterResource(id = R.drawable.video),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp)
                .background(Color.Black, shape = CircleShape)
                .padding(4.dp),
            tint = Color.White
        )
    }
}

fun getVideoThumbnail(context: Context, uri: Uri): Bitmap? {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val retriever = MediaMetadataRetriever()
            retriever.setDataSource(context, uri)
            retriever.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        } else {
            context.contentResolver.openInputStream(uri)?.use { inputStream ->
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeStream(inputStream, null, options)

                options.inSampleSize = calculateInSampleSize(options, 130, 130)

                context.contentResolver.openInputStream(uri)?.use { newInputStream ->
                    options.inJustDecodeBounds = false
                    BitmapFactory.decodeStream(newInputStream, null, options)
                }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

private fun calculateInSampleSize(
    options: BitmapFactory.Options,
    reqWidth: Int,
    reqHeight: Int
): Int {
    val height = options.outHeight
    val width = options.outWidth
    var inSampleSize = 1

    if (height > reqHeight || width > reqWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2

        while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}






