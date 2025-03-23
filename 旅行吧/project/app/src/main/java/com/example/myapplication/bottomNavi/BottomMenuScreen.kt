package com.example.myapplication.bottomNavi

import com.example.myapplication.R

sealed class BottomMenuScreen(
    val route: String,
    val icon: Int,
    val title: String,
) {
    object home : BottomMenuScreen(
        route = "travel",//路径
        icon = R.drawable.travel,
        title = "旅行",//内容
    )

    object person : BottomMenuScreen(
        route = "person",
        icon = R.drawable.person,
        title = "我的",
    )

    object find : BottomMenuScreen(
        route = "find",
        icon = R.drawable.find,
        title = "发现",
    )
}