package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.myapplication.Data.DataGet.getLoginData
import com.example.myapplication.Data.DataGet.getUserData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.R

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TravelView(navController: NavHostController, title: String, text: String,uri:String) {
    System.out.println("!!!!!!!!!!")
    val pictures = mutableListOf<String>()
    val picture = mutableListOf<String>()
    for(u in uri.split(";")){
        pictures.add(u)
    }
    for(i in 0..pictures.size-2){
        var tempS ="";
        for(j in 0..pictures[i].length-1){
            if(pictures[i][j] == ':' && pictures[i][j-6] == '/'){
                tempS+="%3A";
            }
            else{
                tempS+=pictures[i][j]
            }
        }
        picture.add(tempS)
        System.out.println(tempS)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Row {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight()
                        )
                    }
                    /*
                    AsyncImage(
                        model = note.profile,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxHeight()
                    )

                     */
                    val myDatabaseHelper = MyDatabaseHelper(LocalContext.current,"android.db");
                    Text(
                        text = getUserData(myDatabaseHelper)[getLoginData(myDatabaseHelper)[0].currentLogin-1].userName,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                            .padding(4.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 24.sp
                    )
                }
            }
        },
        content  = {
            LazyColumn(
                modifier = Modifier.wrapContentSize(Alignment.Center)
            ) {
                item {
                    Spacer(modifier = Modifier.padding(4.dp))
                    val pagerState = rememberPagerState(
                        pageCount = { picture.size }, // 总页数
                        initialPage = 0 // 初始化显示第一页
                    )
                    var config = LocalConfiguration.current
                    var width = config.screenWidthDp
                    var height = config.screenHeightDp
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier
                            .size(width = (width * 0.95).dp, height = (height * 0.6).dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(start = (width * 0.05).dp)
                    ) { page ->
                        Image(
                            painter = rememberImagePainter(
                                data = picture[page].toUri(), // 确保 picture[0] 是有效的本地存储路径
                                builder = {
                                    crossfade(true)
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize(),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Spacer(modifier = Modifier.padding(4.dp))
                    Row(
                        Modifier
                            .height(50.dp)
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
                        text = text,
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(start = (width * 0.05).dp),
                        fontSize = 24.sp

                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Row(
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .padding(start = (width * 0.05).dp),
                    ) {
                        Icon(
                            painterResource(id = R.drawable.love),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.CenterEnd)
                                .clickable {

                                }
                        )
                        Icon(
                            painterResource(id = R.drawable.like),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.CenterEnd)
                                .clickable {

                                }
                        )
                    }

                    Spacer(modifier = Modifier.padding(16.dp))
                    Divider(
                        color = Color.DarkGray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .width(width = (width * 0.95).dp)
                            .padding(start = (width * 0.05).dp),
                    )
                    Spacer(modifier = Modifier.padding(32.dp))
                }

            }
        }
    )


}
