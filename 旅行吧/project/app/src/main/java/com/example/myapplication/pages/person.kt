package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.myapplication.Data.DataGet.getLikeData
import com.example.myapplication.Data.DataGet.getLoginData
import com.example.myapplication.Data.DataGet.getUserData
import com.example.myapplication.Data.DataGet.getUserNoteData
import com.example.myapplication.Data.DataGet.updateUserData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.addlaunch
import com.example.myapplication.showNote
import com.example.myapplication.topNavi.subTopMenuScreen
import com.example.myapplication.viewmodel.ThemeViewModel
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.ui.theme.darkgreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable

fun Person_after(
    navController: NavController,
    themeViewModel: ThemeViewModel,
    selectedUris_avater: Uri,
    onSelectImages: () -> Unit,
) {
    Scaffold(
        topBar = {
        },
        content = {
            var config = LocalConfiguration.current
            var width = config.screenWidthDp
            var height = config.screenHeightDp

            LazyColumn()
            {
                item {
                    Box(
                        modifier = Modifier
                            .height(240.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bkgr),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                        Column {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(60.dp)
                                    .background(Color.Transparent)

                            ) {
                                Row {
                                    Text(
                                        text = " 个人中心",
                                        color = Color.Black,
                                        fontSize = 18.sp,
                                        modifier = Modifier
                                            .padding(18.dp)
                                            .wrapContentHeight()
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_settings_24),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .padding(top = 18.dp, end = 24.dp) // 设置右侧边距
                                            .clickable {
                                                navController.navigate("setting")
                                            }
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth(0.5f)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(10.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .padding(start = 42.dp)
                                                .size(100.dp) // 外层 Box 大小
                                                .background(Color(0xFFC1E299), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(80.dp) // 内层 Box 大小
                                                    .clip(CircleShape)
                                                    .background(Color.Gray) // Optional: Provide a background to visualize the clipping area

                                            ) {
                                                /*

                                                //System.out.println("avatar = "+nowUser.avatar)

                                                 */
                                                val myDatabaseHelper = MyDatabaseHelper(
                                                    LocalContext.current,
                                                    "android.db"
                                                );
                                                val value: Pair<String, String> = getUserMessage(
                                                    LocalContext.current
                                                )
                                                val uid = value.second.toInt()
                                                val nowUser =
                                                    getUserData(myDatabaseHelper)[uid - 1];
                                                System.out.println("avater" + nowUser.avatar + "stringURI" + selectedUris_avater)
                                                if (selectedUris_avater.toString() != "null") {
                                                    updateUserData(
                                                        myDatabaseHelper,
                                                        nowUser.userID,
                                                        selectedUris_avater.toString()
                                                    )

                                                }

                                                if (getUserData(myDatabaseHelper)[uid - 1].avatar=="null") {
                                                    Image(
                                                        painter = painterResource(id = R.drawable.default123),
                                                        contentDescription = "头像",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier
                                                            .fillMaxHeight()
                                                            .clickable {
                                                                CoroutineScope(Dispatchers.Main).launch {
                                                                    onSelectImages()  // 等待直到图片被选中
                                                                    System.out.println("nowx" + selectedUris_avater)
                                                                    if (selectedUris_avater != "null".toUri()) {
                                                                        updateUserData(
                                                                            myDatabaseHelper,
                                                                            nowUser.userID,
                                                                            selectedUris_avater.toString()
                                                                        )


                                                                    }

                                                                }
                                                            }
                                                    )
                                                } else {
                                                    AsyncImage(
                                                        model = getUserData(myDatabaseHelper)[uid - 1].avatar.toUri(),
                                                        contentDescription = "头像",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier
                                                            .fillMaxHeight()
                                                            .clickable {
                                                                CoroutineScope(Dispatchers.Main).launch {
                                                                    onSelectImages()  // 等待直到图片被选中
                                                                    if (selectedUris_avater != "null".toUri()) {
                                                                        updateUserData(
                                                                            myDatabaseHelper,
                                                                            nowUser.userID,
                                                                            selectedUris_avater.toString()
                                                                        )
                                                                    }

                                                                }


                                                            }
                                                    )

                                                }

                                            }
                                        }
                                        Text(
                                            text = ":这个人很懒，还没有签名~",
                                            fontSize = 12.sp,
                                            modifier = Modifier
                                                .wrapContentWidth()
                                                .align(Alignment.End)
                                                .padding(top = 12.dp, start = 24.dp)
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .fillMaxWidth()
                                    //contentAlignment = Alignment.CenterStart
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(top = (0.055 * width).dp)
                                    ) {
                                        val context = LocalContext.current
                                        val value: Pair<String, String> = getUserMessage(context)
                                        val name: String = value.first
                                        val uid: String = value.second
                                        Box(
                                            modifier = Modifier.align(Alignment.Start)
                                        ) {
                                            Column {
                                                Text(
                                                    text = name,
                                                    color = Color.Black,
                                                    fontWeight = FontWeight.Bold,
                                                    letterSpacing = 2.sp,
                                                    fontSize = 32.sp
                                                )
                                                Spacer(modifier = Modifier.padding(4.dp))
                                                Text(
                                                    text = "uid:$uid",
                                                    color = Color.DarkGray,
                                                    fontSize = 16.sp
                                                )
                                                Spacer(modifier = Modifier.padding(6.dp))
                                            }
                                        }
                                        Row(
                                            modifier = Modifier
                                                .align(Alignment.Start)
                                                .wrapContentHeight()
                                        ) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.badge2),
                                                contentDescription = null,
                                                modifier = Modifier.size(48.dp),
                                                tint = Color.Unspecified
                                            )
                                            Spacer(modifier = Modifier.padding(4.dp))
                                            Icon(
                                                painter = painterResource(id = R.drawable.badge1),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(46.dp)
                                                    .padding(top = 2.dp),
                                                tint = Color.Unspecified
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                    val myCard = listOf<Pair<String, Int>>(
                        Pair("我的主题", R.drawable.market),
                        Pair("我的收藏", R.drawable.collect),
                        Pair("我的成就", R.drawable.achievement),
                    );
                    Spacer(modifier = Modifier.padding(8.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))
                        Card(
                            modifier = Modifier
                                .weight(0.2f)
                                .height(60.dp)
                                .align(Alignment.CenterVertically)
                                .border(2.dp, darkgreen, CircleShape),
                            elevation = 0.dp
                        ) {
                            Text(
                                text = "我的主题",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize(
                                        Alignment.Center
                                    )
                                    .clickable {
                                        navController.navigate("market");
                                    }
                                    .padding(vertical = 12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        Card(
                            modifier = Modifier
                                .weight(0.2f)
                                .height(60.dp)
                                .align(Alignment.CenterVertically)
                                .border(2.dp, darkgreen, CircleShape),
                            elevation = 0.dp
                        ) {
                            Text(
                                text = "我的成就",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize(
                                        Alignment.Center
                                    )
                                    .padding(vertical = 12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        Card(
                            modifier = Modifier
                                .weight(0.2f)
                                .height(60.dp)
                                .align(Alignment.CenterVertically)
                                .border(2.dp, darkgreen, CircleShape),
                            elevation = 0.dp
                        ) {
                            Text(
                                text = "我的旅途",
                                fontSize = 14.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .wrapContentSize(
                                        Alignment.Center
                                    )
                                    .padding(vertical = 12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                    }
                    Spacer(modifier = Modifier.padding(16.dp))
                    Divider(
                        color = Color.DarkGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(width = (width).dp)
                        //.padding(start = (width * 0.05).dp),
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    subNavi(navController)
                    Spacer(modifier = Modifier.padding(24.dp))

                }


            }

        },
        bottomBar = {
            Column {

                addlaunch(navController = navController)
                Spacer(modifier = Modifier.padding(15.dp))
                BottomMenu(navController);
            }

        }
    )
}



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun subNote(subnavController: NavController, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            topnavi(navController = subnavController)
            val myDatabaseHelper = MyDatabaseHelper(LocalContext.current, "android.db");
            val currentID = getLoginData(myDatabaseHelper)
            val nowlikes = getLikeData(myDatabaseHelper, currentID[0].currentLogin)
            if (nowlikes.size == 0) {
                Spacer(modifier = Modifier.padding(100.dp))
                Text(
                    "收藏的笔记会出现在这里",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                );
            } else {
                for (nowlike in nowlikes) {
                    val currentNote =
                        showNote(MyDatabaseHelper(LocalContext.current, nowlike.topic + ".db"))
                    val note = currentNote[nowlike.id];
                    val note_pic = note.picture.split("\n");
                    Spacer(modifier = Modifier.padding(12.dp))
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.9f)
                            .height(108.dp)
                            .clickable {
                                navController.navigate("NoteView/${note.topic}/${note.id - 1}")
                            }
                    ) {
                        Card(modifier = Modifier.fillMaxSize(), elevation = 10.dp) {
                            Row(
                            ) {
                                Text(
                                    text = note.title,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .fillMaxWidth(0.6f)
                                        .padding(start = 32.dp),
                                    //.border(2.dp, Color.Black),
                                    fontSize = 18.sp,
                                    letterSpacing = 1.sp,
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.width(28.dp))
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .clip(RoundedCornerShape(10.dp))
                                        .size(80.dp)
                                        .aspectRatio(1f),
                                ) {
                                    AsyncImage(
                                        model = note.cover,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }

                    }
                }
            }

        }

    }

}

/*
var uid:Long,
var name:String,
var phone:String,
var pwd:String,
var fans:Int,
var follows:Int,
var likes:Int,
var avater:Int,

 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun subTravelNote(subnavController: NavController, navController: NavController) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            topnavi(navController = subnavController)

            val myDatabaseHelper = MyDatabaseHelper(LocalContext.current, "android.db");
            val currentID = getLoginData(myDatabaseHelper)
            val nownotes = getUserNoteData(myDatabaseHelper, currentID[0].currentLogin)
            if (nownotes.size == 0) {
                Spacer(modifier = Modifier.padding(12.dp))
                Text(
                    "暂时还没有游记哦，要写一篇吗",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                );
            } else {
                for (nownote in nownotes) {
                    val texts = nownote.text;
                    val text = texts.split("!");
                    val picture: MutableList<Uri> = mutableListOf()
                    for (pic in text[0].split(";")) {
                        picture.add(pic.toUri())
                    }
                    val title = text[1];
                    val body = text[2];
                    Spacer(modifier = Modifier.padding(8.dp))
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.9f)
                            .height(108.dp)
                            .clickable {
                                //System.out.println("!!!!+!!!!" + title + " " + body + " " + text[0])
                                val encodedTitle =
                                    URLEncoder.encode(title, StandardCharsets.UTF_8.toString())
                                val encodedBody =
                                    URLEncoder.encode(body, StandardCharsets.UTF_8.toString())
                                val encodedUri =
                                    URLEncoder.encode(text[0], StandardCharsets.UTF_8.toString())
                                val navigateUrl =
                                    "TravelView/${encodedTitle}/${encodedBody}/${encodedUri}"
                                navController.navigate("TravelView/${encodedTitle}/${encodedBody}/${encodedUri}")
                            }
                    ) {
                        Card(modifier = Modifier
                            .fillMaxSize(),
                            elevation = 6.dp
                        ) {
                            Row(
                            ) {
                                Text(
                                    text = title,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .fillMaxWidth(0.6f)
                                        .padding(start = 32.dp),
                                        //.border(2.dp, Color.Black),
                                    fontSize = 18.sp,
                                    letterSpacing = 1.sp,
                                    textAlign = TextAlign.Left
                                )
                                Spacer(modifier = Modifier.width(28.dp))
                                    //.padding(start = 36.dp, end = 16.dp)
                                Box(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .clip(RoundedCornerShape(10.dp))
                                        .size(80.dp)
                                        .aspectRatio(1f),
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(ImageRequest.Builder(
                                            LocalContext.current
                                        ).data(
                                            data = picture[0] // 确保 picture[0] 是有效的本地存储路径
                                        ).apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                        }).build()
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.height(42.dp))
            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun subQuery(navController: NavController) {
    Box(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            topnavi(navController = navController)
            Spacer(modifier = Modifier.padding(150.dp))
            Text(
                "暂时还没有问答哦，要写一篇吗",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            );
        }

    }
}

@Composable
fun subNavi(navController: NavController) {
    val subNavController = rememberNavController();
    NavHost(subNavController, "subNote") {
        composable("subNote") {//笔记
            subNote(subNavController, navController)
        }
        composable("subTravelNote") {//游记
            subTravelNote(subNavController, navController)
        }
        composable("subQuery") {//问答
            subQuery(subNavController)
        }
    }
}

@Composable
fun topnavi(navController: NavController) {
    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
    ) {
        val menuItems = mutableListOf<subTopMenuScreen>(
            subTopMenuScreen.subNote,
            subTopMenuScreen.subTravelNote,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(100.dp),
            // 居中对齐 LazyRow 中的内容
            verticalAlignment = Alignment.CenterVertically,
            // 使用 fillMaxWidth 来填充横向空间
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            items(menuItems) {
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(it.route)
                    }
                ) {
                    Text(text = it.title)
                }

            }
        }
    }
}

fun getUserMessage(context: Context): Pair<String, String> {
    val myDatabaseHelper = MyDatabaseHelper(context, "android.db");
    val currentUser = getLoginData(myDatabaseHelper)
    val allUser = getUserData(myDatabaseHelper);
    for (user in allUser) {
        if (user.userID == currentUser[0].currentLogin) {
            return Pair(user.userName, user.userID.toString());
        }
    }
    return Pair("Error", "-1")
}