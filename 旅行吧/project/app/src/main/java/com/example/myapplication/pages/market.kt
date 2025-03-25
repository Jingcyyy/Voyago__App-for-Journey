package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.ui.theme.ThemeColor
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.viewmodel.ThemeViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun market(navController: NavController, themeViewModel: ThemeViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.Default.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.fillMaxHeight(),
                                tint = Color.Black
                            )
                        }
                        Text(
                            text = "我的主题",
                            modifier = Modifier
                                .wrapContentSize(Alignment.Center)
                                .padding(4.dp)
                                .align(Alignment.CenterVertically),
                            color = Color.Black
                        )
                    }
                },
                backgroundColor = Color(0xFFF6FAEB)
            )
        },
        content = {
            var config = LocalConfiguration.current;
            val width = config.screenWidthDp;
            val height = config.screenHeightDp;
            LazyColumn(
                modifier = Modifier.wrapContentSize(Alignment.Center)
            ) {
                val colorsWithLabels: List<List<Pair<Color, String>>> = listOf(
                    listOf(
                        Pair(Color(0xFF3A96A8), "蓝色"),
                        Pair(Color(0xFF033345), "033345"),
                        Pair(Color(0xFF7683D6), "7683D6"),
                        Pair(Color(0xFF3D82AC), "3D82AC")
                    ),
                    listOf(
                        Pair(Color(0xFFFBFFB9), "大地色"),
                        Pair(Color(0xFFFDD692), "033345"),
                        Pair(Color(0xFFEC7357), "7683D6"),
                        Pair(Color(0xFF754F44), "3D82AC")
                    ),
                    listOf(
                        Pair(Color(0xFFF7AA97), "橘红"),
                        Pair(Color(0xFFED9282), "033345"),
                        Pair(Color(0xFFDE7E73), "7683D6"),
                        Pair(Color(0xFFCFAA9E), "3D82AC")
                    ),
                    listOf(
                        Pair(Color(0xFFcff09e), "绿色"),
                        Pair(Color(0xFFa8dba8), "033345"),
                        Pair(Color(0xFF79bd9a), "7683D6"),
                        Pair(Color(0xFF3b8686), "3D82AC")
                    ),
                )
                items(colorsWithLabels) {
                    Card(
                        elevation = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(40.dp)  // Adjust height as needed
                    ) {
                        Column(

                        ) {
                            Spacer(modifier = Modifier.padding(5.dp))
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = it[0].second,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentSize(Alignment.Center),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.padding(5.dp))
                            Row(
                                modifier = Modifier.height(275.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                        .background(it[0].first),
                                ) {}
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                        .background(it[1].first),
                                ) {}
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                        .background(it[2].first),
                                ) {}
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .weight(1f)
                                        .background(it[3].first),
                                ) {}
                            }
                            Text(
                                "应用",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .wrapContentSize(Alignment.Center)
                                    .clickable {
                                        themeViewModel.updateColor(ThemeColor(it[0].first,it[1].first,it[2].first,it[3].first))
                                    },
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,

                                )
                        }


                    }

                    // Below are the color bars with labels


                }
            }

        },
        bottomBar = {
        }
    )
}



