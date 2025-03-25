package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.darkgreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun setting(navController: NavController) {
    Column {
        TopAppBar(
            title = {
                val config = LocalConfiguration.current;
                val width = config.screenWidthDp;
                val height = config.screenHeightDp;

                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.fillMaxHeight(),
                        tint = Color.White
                    )
                }
                Text(
                    text = "设置",
                    color = Color.White,
                    modifier = Modifier.width((width).dp),
                    textAlign = TextAlign.Left
                )


            },
            backgroundColor = darkgreen
        )


        LazyColumn {
            item {
                val showDialog = remember {
                    mutableStateOf(false)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                                .padding(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "账号与安全",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            navController.navigate("register")
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "隐私设置",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                            }

                        }

                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                                .padding(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "通知设置",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()

                                )
                                Text(
                                    text = "通用设置",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "添加小组件",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                            }
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                                .padding(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "青少年模式",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "内容偏好调节",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "深色模式",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "字体大小",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                            }
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 20.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column(
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                                .padding(20.dp)
                        ) {
                            Column {
                                Text(
                                    text = "帮助与客服",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "鼓励一下",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "个人信息收集清单",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "第三方信息共享清单",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "关于我们",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                                Divider(
                                    color = Color.DarkGray,
                                    thickness = 1.dp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Text(
                                    text = "联系我们",
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp).clickable {
                                            showDialog.value = true
                                        }
                                )
                            }
                        }
                    }

                }
                var config = LocalConfiguration.current
                var width = config.screenWidthDp
                var height = config.screenHeightDp
                Spacer(modifier = Modifier.padding(16.dp))
                Divider(
                    color = Color.DarkGray,
                    thickness = 1.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding((0.05 * width).dp)
                )
                Spacer(modifier = Modifier.padding(24.dp))
                if (showDialog.value) {
                    AlertDialog(onDismissRequest = { showDialog.value = false },
                        title = {
                            Text(text = "提示")
                        },
                        text = { Text(text = "此功能还在开发中") },
                        confirmButton = {
                            Button(onClick = { showDialog.value = false }) {
                                Text(text = "确认")
                            }
                        }
                    )
                }


            }


        }
    }


}
