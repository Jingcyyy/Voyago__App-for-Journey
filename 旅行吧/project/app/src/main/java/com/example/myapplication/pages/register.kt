package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.Data.DataGet.deleteLoginData
import com.example.myapplication.Data.DataGet.getUserData
import com.example.myapplication.Data.DataGet.insertLoginData
import com.example.myapplication.Data.DataGet.insertUserData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.selectedUris_avater
import com.example.myapplication.ui.theme.darkgreen

@Composable
fun register(navController: NavController) {
    val config = LocalConfiguration.current;
    val wid = config.screenWidthDp;
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .size(600.dp, 800.dp)
                .align(Alignment.Center)
        ) {
            RegisterNavi(navController)
        }
    }
}


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Lo(subnavController: NavController, navController: NavController) {
    var textName = remember { mutableStateOf("") }
    var textPwd = remember { mutableStateOf("") }
    val context = LocalContext.current
    val showDialog = remember {
        mutableStateOf(false)
    }
    Column {
        RegisterTopNavi(navController = subnavController, 1)
        Box(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(400.dp, 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card() {
                        TextField(
                            value = textName.value,
                            onValueChange = {
                                textName.value = it
                            },
                            placeholder = {
                                Text(text = "请输入名称")
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.White
                                ),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Left,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(400.dp, 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card() {
                        TextField(
                            value = textPwd.value,
                            onValueChange = {
                                textPwd.value = it
                            },
                            placeholder = {
                                Text(text = "请输入密码")
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.White
                                ),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Left,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp, 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            "确认",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.White
                                )
                                .clip(CircleShape)
                                .clickable {
                                    if (CheckUserisExist(textName.value, context) == true) {
                                        if (CheckUserPwd(textName.value, textPwd.value, context) == true
                                        ) {

                                            UpdateCurrentLogin(textName.value, context);
                                            selectedUris_avater = "null".toUri()
                                            navController.navigate("person");
                                        } else {
                                            showDialog.value = true
                                        }
                                    } else {
                                        showDialog.value = true
                                    }

                                },
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )


                    }
                }

            }

        }
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = "提示")
            },
            text = { Text(text = "出现错误") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "确认")
                }
            }
        )
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Re(subnavController: NavController, navController: NavController) {
    var textName = remember { mutableStateOf("") }
    var textPwd = remember { mutableStateOf("") }
    val context = LocalContext.current
    val showDialog = remember {
        mutableStateOf(false)
    }
    Column {
        RegisterTopNavi(navController = subnavController, 2)
        Box(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopCenter)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(400.dp, 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card() {
                        TextField(
                            value = textName.value,
                            onValueChange = {
                                textName.value = it
                            },
                            placeholder = {
                                Text(text = "请输入账号")
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.White
                                ),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Left,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(400.dp, 100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card() {
                        TextField(
                            value = textPwd.value,
                            onValueChange = {
                                textPwd.value = it;
                            },
                            placeholder = {
                                Text(text = "请输入密码")
                            },
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Color.White
                                ),
                            singleLine = true,
                            textStyle = TextStyle(
                                textAlign = TextAlign.Left,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp, 50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.wrapContentSize(Alignment.Center)
                    ) {
                        Text(
                            "确认",
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    Color.White
                                )
                                .clip(CircleShape)
                                .clickable {
                                    if (CheckUserisExist(textName.value, context) == true) {
                                        showDialog.value = true
                                    } else {
                                        InsertUser(textName.value, textPwd.value, context)
                                        subnavController.navigate("Lo")
                                    }
                                    System.out.println("注册时：" + textName.value + " " + textPwd.value)
                                },
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )


                    }
                }

            }

        }
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = "提示")
            },
            text = { Text(text = "出现错误") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "确认")
                }
            }
        )
    }
}

@Composable
fun RegisterNavi(navController: NavController) {
    val subNavController = rememberNavController();
    NavHost(subNavController, "Lo") {
        composable("Re") {//笔记
            Re(subNavController, navController)
        }
        composable("Lo") {//游记
            Lo(subNavController, navController)
        }
    }
}

@Composable
fun RegisterTopNavi(navController: NavController, select: Int) {
    Row(
        modifier = Modifier
            .padding(top = 60.dp)
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val menuItems = mutableListOf<Pair<String, String>>(
            Pair("Lo", "登录"), Pair("Re", "注册")
        )
        if (select == 1) {
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
                    if (it.first == "Lo") {
                        Column(
                            modifier = Modifier.clickable {
                                navController.navigate(it.first)
                            }
                        ) {
                            Text(text = it.second, fontSize = 32.sp, color = darkgreen)
                        }
                    } else {
                        Column(
                            modifier = Modifier.clickable {
                                navController.navigate(it.first)
                            }
                        ) {
                            Text(text = it.second, fontSize = 32.sp)
                        }
                    }

                }
            }
        } else {
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
                    if (it.first == "Re") {
                        Column(
                            modifier = Modifier.clickable {
                                navController.navigate(it.first)
                            }
                        ) {
                            Text(text = it.second, fontSize = 32.sp, color = darkgreen)
                        }
                    } else {
                        Column(
                            modifier = Modifier.clickable {
                                navController.navigate(it.first)
                            }
                        ) {
                            Text(text = it.second, fontSize = 32.sp)
                        }
                    }

                }
            }
        }
    }
}

fun CheckUserisExist(textName: String, context: Context): Boolean {
    val myUserDataBase = MyDatabaseHelper(context, "android.db");
    val allUser = getUserData(myUserDataBase);
    System.out.println("!")
    for (user in allUser) {

        if (user.userName == textName) {
            return true;
        }
    }
    return false
}

fun CheckUserPwd(textName: String, textPwd: String, context: Context): Boolean {
    val myUserDataBase = MyDatabaseHelper(context, "android.db");
    val allUser = getUserData(myUserDataBase);

    for (user in allUser) {
        if (user.userName == textName) {
            if (user.pwd == textPwd) {
                return true;
            } else return false;
        }
    }
    return false
}

fun UpdateCurrentLogin(textName: String, context: Context) {
    val myUserDataBase = MyDatabaseHelper(context, "android.db");
    val allUser = getUserData(myUserDataBase);
    for (user in allUser) {
        if (user.userName == textName) {
            val myLoginDatabase = MyDatabaseHelper(context, "android.db")
            deleteLoginData(myLoginDatabase);
            insertLoginData(myLoginDatabase, user.userID)
            return;
        }
    }
}

fun InsertUser(textName: String, textPwd: String, context: Context) {
    val myUserDataBase = MyDatabaseHelper(context, "android.db");
    val alluser = getUserData(myUserDataBase);
    insertUserData(myUserDataBase, alluser.size + 1, textName, textPwd, "null");
}