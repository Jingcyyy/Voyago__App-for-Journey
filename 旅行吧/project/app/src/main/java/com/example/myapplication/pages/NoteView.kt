package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.myapplication.Data.DataGet.deleteLikeData
import com.example.myapplication.Data.DataGet.getLikeData
import com.example.myapplication.Data.DataGet.insertLikeData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.showNote
import com.example.myapplication.ui.theme.darkgreen

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteView(navController: NavHostController, topic: String, id: Int) {
    val currentNote = showNote(MyDatabaseHelper(LocalContext.current, topic + ".db"))
    val note = currentNote[id];
    val note_pic = note.picture.split("\n");
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color(0xFFF6FAEB),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Row {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    AsyncImage(
                        model = note.profile,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight()
                            .clip(CircleShape)
                    )
                    Text(
                        text = "作者",
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .padding(8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier.wrapContentSize(Alignment.Center)
            ) {
                item {
                    Spacer(modifier = Modifier.padding(2.dp))
                    val pagerState = rememberPagerState(
                        pageCount = { note_pic.size - 1 }, // 总页数
                        initialPage = 0 // 初始化显示第一页
                    )
                    var config = LocalConfiguration.current
                    var width = config.screenWidthDp
                    var height = config.screenHeightDp
                    var context = LocalContext.current
                    val myDatabaseHelper = MyDatabaseHelper(context,"android.db");
                    val likes = getLikeData(myDatabaseHelper, getUserMessage(context).second.toInt())
                    var bool = false;
                    for(like in likes){
                        if(like.id == id && like.topic == topic){
                            bool = true;
                            break;
                        }
                    }
                    var isClick = remember {
                        mutableStateOf(bool);
                    }
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .size(width = (width * 0.98).dp, height = (height * 0.6).dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(start = (width * 0.02).dp)
                    ) { page ->
                        AsyncImage(
                            model = note_pic[page],
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(
                        Modifier
                            .height(30.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(pagerState.pageCount) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(10.dp)
                            )
                        }
                    }
                    Text(
                        text = note.textDetails,
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(start = (width * 0.05).dp),
                        fontSize = 16.sp

                    )
                    Spacer(modifier = Modifier.padding(32.dp))
                    Row(
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .padding(start = (width * 0.05).dp),
                    ) {
                        Text(text = note.timeDetails, fontSize = 12.sp)
                        if(isClick.value == false){
                            Icon(
                                painterResource(id = R.drawable.like1),
                                tint = Color.Unspecified,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.CenterEnd)
                                    .clickable {
                                        val myDatabaseHelper = MyDatabaseHelper(context, "android.db");
                                        val value: Pair<String, String> = getUserMessage(context)
                                        insertLikeData(
                                            myDatabaseHelper,
                                            topic,
                                            id,
                                            value.second.toInt()
                                        )
                                        isClick.value = true;
                                    }
                            )
                        }
                        else{
                            Icon(
                                painterResource(id = R.drawable.like2),
                                tint = Color.Unspecified,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(4.dp)
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.CenterEnd)
                                    .clickable {
                                        val myDatabaseHelper = MyDatabaseHelper(context, "android.db");
                                        val value: Pair<String, String> = getUserMessage(context)
                                        deleteLikeData(
                                            myDatabaseHelper,
                                            topic,
                                            id,
                                            value.second.toInt()
                                        )
                                        isClick.value = false;
                                    }
                            )
                        }

                    }

                    Spacer(modifier = Modifier.padding(18.dp))
                    Divider(
                        color = darkgreen,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .padding(start = (width * 0.05).dp),
                    )
                    Spacer(modifier = Modifier.padding(18.dp))
                }

            }
        }
    )


}
