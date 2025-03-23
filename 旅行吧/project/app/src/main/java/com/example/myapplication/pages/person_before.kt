package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.Data.DataGet.getUserData
import com.example.myapplication.Data.DataGet.updateUserData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.selectedUris_avater
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.viewmodel.ThemeViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Person_before(navController: NavController, themeViewModel: ThemeViewModel) {
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


                                                    Image(
                                                        painter = painterResource(id = R.drawable.default123),
                                                        contentDescription = "头像",
                                                        contentScale = ContentScale.Crop,
                                                        modifier = Modifier
                                                            .fillMaxHeight()
                                                            .clickable {
                                                                navController.navigate("register")
                                                            }
                                                    )


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
                                            .padding(top = (0.02 * width).dp)
                                    ) {
                                        val context = LocalContext.current
                                        val value: Pair<String, String> = getUserMessage(context)
                                        val name: String = "登录/注册"
                                        Box(
                                            modifier = Modifier.align(Alignment.Start)
                                        ) {
                                            Column {
                                                Spacer(modifier = Modifier.padding(10.dp))
                                                Text(
                                                    text = name,
                                                    color = Color.Black,
                                                    fontWeight = FontWeight.Medium,
                                                    fontSize = 28.sp,
                                                    modifier = Modifier.clickable {
                                                        navController.navigate("register")
                                                    }
                                                )
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
                    Spacer(modifier = Modifier.padding(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(0.1f))
                        Card(
                            modifier = Modifier
                                .weight(0.25f)
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
                                .weight(0.25f)
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
                                .weight(0.25f)
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
                    Spacer(modifier = Modifier.padding(12.dp))
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
            BottomMenu(navController);
        }
    )
}