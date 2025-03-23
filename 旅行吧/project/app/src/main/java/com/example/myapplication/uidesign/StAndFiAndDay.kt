package com.example.myapplication.uidesign

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.R
import com.example.myapplication.functions.generateMap
import com.example.myapplication.functions.generateRoute
import com.example.myapplication.functions.startfind_onlysize
import com.example.myapplication.showAtt
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.viewmodel.FindViewModel
import com.example.myapplication.viewmodel.ThemeViewModel
import com.wxy.chinamapview.view.ChinaMapView

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun StAndFiAndDay(
    findViewModel: FindViewModel,themeViewModel:ThemeViewModel
) {
    val searchText1 by findViewModel.attstart.collectAsState()
    val searchText2 by findViewModel.attfinal.collectAsState()
    val searchText3 by findViewModel.attnum.collectAsState()
    val setisOK by findViewModel.setisok.collectAsState()
    val showDialog = remember {
        mutableStateOf(false)
    }
    val typeList = mutableListOf(
        "北京",
        "上海",
        "广州",
        "武汉",
        "西安",
        "成都",
    )
    val attList =
        listOf(
            "颐和园",
            "故宫",
            "八达岭长城",
            "天坛公园",
            "天安门广场",
            "恭王府",
            "奥林匹克森林公园",
            "明十三陵",
            "慕田峪长城",
            "石林峡",
            "什刹海",
            "圆明园",
            "玉渊潭公园",
            "北京动物园",
            "北海公园",
            "北京野生动物园",
            "国家植物园",
            "北京欢乐谷",
            "十渡",
            "世界公园",
            "景山公园",
            "香山公园",
            "八大处公园",
            "大运河森林公园",
            "雁栖湖",
            "陶然亭",
            "北京国际鲜花港",
            "凤凰岭",
            "朝阳公园",
            "北京园博园",
            "北宫国家森林公园",
            "首都博物馆",
            "世界花卉大观园",
            "黄花城水长城",
            "红螺寺",
            "卢沟桥",
            "百里画廊",
            "北京海洋馆",
            "奥林匹克公园",
            "中央电视台",
            "青龙峡",
            "龙潭公园",
            "奥林匹克水上公园",
            "金海湖",
            "元大都遗址公园",
            "紫竹院公园",
            "龙庆峡",
            "蓝调薰衣草庄园",
            "地坛公园",
            "野鸭湖湿地公园",
            "日坛公园",
            "地质公园",
            "中国科技馆",
            "孔庙和国子监博物馆",
            "北京圣莲山",
            "银山塔林",
            "周口店遗址博物馆",
            "汽车博物馆",
            "中国人民抗日战争纪念馆",
            "中山公园"
        )

    var isClick by remember { mutableStateOf(false) }
    var text1isClick by remember { mutableStateOf(false) }
    var selectType by rememberSaveable { mutableStateOf("北京") }
    var expanded1 by remember {
        mutableStateOf(
            false
        )
    }
    var expanded2 by remember {
        mutableStateOf(
            false
        )
    }
    showAtt(
        MyDatabaseHelper(LocalContext.current, "attraction.db"),
        selectType,
        LocalContext.current
    );//获取景点信息
    Column {///////////////////////////***
        Card {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.2f)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.local),
                        contentDescription = "北京",
                        Modifier
                            .width(28.dp)
                            .padding(top = 12.dp)
                            .align(Alignment.End),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        Button(
                            onClick = { isClick = !isClick },
                            content = {
                                Text(text = selectType,
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    //modifier = Modifier.align(Alignment.CenterHorizontally)
                                )
                            },
                            colors = ButtonDefaults.buttonColors(themeViewModel.ThemeColor.value.second),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .clip(CircleShape)
                                .height(60.dp)
                        )
                        if (isClick) {
                            DropdownMenu(
                                expanded = isClick,
                                modifier = Modifier.fillMaxWidth(),
                                onDismissRequest = {
                                     isClick=!isClick;
                                },
                                content = {
                                    typeList.forEach {
                                        DropdownMenuItem(
                                            onClick = {
                                                isClick = !isClick
                                                selectType = it
                                            },
                                            content = {
                                                Text(text = it)
                                            }
                                        )
                                    }
                                },
                            )
                        }

                    }

                }
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(60.dp)
//                            .border(2.dp, Color.Green)
                        ,
                        elevation = 4.dp // 设置阴影高度
                    ) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            OutlinedTextField(
                                value = searchText1,
                                onValueChange = {
                                    findViewModel.updateStart(it)
                                    expanded1 = !expanded1
                                },
                                modifier = Modifier
                                    .fillMaxWidth() // 让TextField填满Card的宽度
                                    .padding(3.dp), // 添加一些内部间距
                                placeholder = { Text("请输入起点" , fontSize = 14.sp)}, // 占位符
                                singleLine = true, // 单行模式
                                shape = MaterialTheme.shapes.small, // TextField的形状
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = darkgreen,
                                    cursorColor = darkgreen,
                                )
                            )
                            if (searchText1 != "" || expanded1) {
                                DropdownMenu(
                                    expanded = (searchText1 != "") && expanded1,
                                    modifier = Modifier
                                        .width((LocalConfiguration.current.screenWidthDp * 0.65 - 16).dp)
                                        .height(180.dp),
                                    onDismissRequest = {
                                        expanded1 = false
                                    },
                                    content = {
                                        var tempList = mutableListOf("")
                                        var tempMap = mutableMapOf<String, Int>();
                                        for (second in attList) {
                                            var cos = 0;
                                            for (first in searchText1) {
                                                for (third in second) {
                                                    if (first == third) {
                                                        cos++;
                                                    }
                                                }
                                            }
                                            if (cos > 0) {
                                                tempMap[second] = cos;
                                            }
                                        }
                                        var tempM = tempMap.toList().sortedBy { (_,value)-> value }.toMap()
                                        for(temp in tempM){
                                            tempList.add(temp.key)
                                        }
                                        var nowList = tempList.toList().reversed()
                                        nowList.forEach {
                                            DropdownMenuItem(
                                                onClick = {
                                                    expanded1 = !expanded1
                                                    findViewModel.updateStart(it)
                                                },
                                                content = {
                                                    Text(text = it, fontSize = 14.sp)
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(24.dp)
                                            )
                                        }
                                    },
                                )
                            }


                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .height(60.dp)
//                            .border(2.dp, Color.Green)
                        ,
                        elevation = 4.dp // 设置阴影高度
                    ) {
                        OutlinedTextField(
                            value = searchText2,
                            onValueChange = {
                                findViewModel.updateFinal(it)
                                expanded2 = !expanded2
                            },
                            modifier = Modifier
                                .fillMaxWidth() // 让TextField填满Card的宽度
                                .padding(3.dp), // 添加一些内部间距
                            placeholder = { Text("终点", fontSize = 14.sp) }, // 占位符
                            singleLine = true, // 单行模式
                            shape = MaterialTheme.shapes.small, // TextField的形状
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = darkgreen,
                                cursorColor = darkgreen,
                            )
                        )
                        if (searchText2 != "" || expanded2) {
                            DropdownMenu(
                                expanded = (searchText2 != "") && expanded2,
                                modifier = Modifier
                                    .width((LocalConfiguration.current.screenWidthDp * 0.65 - 16).dp)
                                    .height(200.dp),
                                onDismissRequest = {
                                    expanded2 = false
                                },
                                content = {
                                    var tempList = mutableListOf("")
                                    var tempMap = mutableMapOf<String, Int>();
                                    for (second in attList) {
                                        var cos = 0;
                                        for (first in searchText2) {
                                            for (third in second) {
                                                if (first == third) {
                                                    cos++;
                                                }
                                            }
                                        }
                                        if (cos > 0) {
                                            tempMap[second] = cos;
                                        }
                                    }
                                    var tempM = tempMap.toList().sortedBy { (_,value)-> value }.toMap()
                                    for(temp in tempM){
                                        tempList.add(temp.key)
                                    }
                                    var nowList = tempList.toList().reversed()
                                    nowList.forEach {
                                        DropdownMenuItem(
                                            onClick = {
                                                expanded2 = !expanded2
                                                findViewModel.updateFinal(it)
                                            },
                                            content = {
                                                Text(text = it)
                                            },
                                            modifier = Modifier.fillMaxWidth()
                                        )
                                    }
                                },
                            )
                        }
                    }

                }
                Column(///////////////////////////////////////////////////////////////////////////////////////
                    modifier = Modifier.weight(0.25f)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)// 添加一些外部间距
                            .height(60.dp),
                        elevation = 4.dp // 设置阴影高度
                    ) {
                        OutlinedTextField(
                            value = searchText3,
                            onValueChange = { findViewModel.updateNum(it) },
                            modifier = Modifier
                                .fillMaxWidth() // 让TextField填满Card的宽度
                                .padding(3.dp), // 添加一些内部间距
                            placeholder = { Text("旅行景点数", fontSize = 12.sp) }, // 占位符
                            singleLine = true, // 单行模式
                            shape = MaterialTheme.shapes.small, // TextField的形状
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = darkgreen,
                                cursorColor = darkgreen,
                            )
                        )
                    }
                    Button(
                        onClick = {
                            findViewModel.updateok()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = darkgreen)
                    ) {
                        Text(text = "生成", color = Color.White, fontSize = 18.sp)
                    }
                }

            }

        }
        Divider(
            color = darkgreen,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.8f)
        )
        Tags();
        Divider(
            color = darkgreen,
            thickness = 1.dp,
            modifier = Modifier
                .fillMaxWidth()
                .alpha(0.4f)
        )
        val config = LocalConfiguration;
        val width = config.current.screenWidthDp;
        val height = config.current.screenHeightDp;
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .align(Alignment.CenterHorizontally)
        ) {
            if (setisOK != 0) {
                Box(
                ) {
                    Column(
                    ) {
                        if(!searchText3.isEmpty()){
                            startfind_onlysize(searchText3.toInt(), findViewModel)

                            generateMap(selectType, findViewModel)
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .background(color = Color(0xFFF6FAEB) )
                            ){
                                Text(
                                    text = "推荐路线",
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(16.dp),
                                    letterSpacing = 1.sp
                                )
                            }

                            Divider(
                                color = darkgreen,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .alpha(0.4f)
                            )
                            generateRoute(findViewModel)
                        }
                        else{

                        }
                    }

                }
            } else {
                AndroidView(factory = {
                    ChinaMapView(it).apply {

                    }
                })
            }
        }
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = "提示")
            },
            text = { Text(text = "预计地点错误") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "确认")
                }
            }
        )
    }

    // 状态记忆，用于保存搜索栏中的文本

}