package com.example.myapplication.topNavi

import com.example.myapplication.R

sealed class subTopMenuScreen(
    val route: String,
    val icon: Int,
    val title: String,
) {
    object subNote : subTopMenuScreen(
        route = "subNote",//路径
        icon = R.drawable.note,
        title = "笔记",//内容
    )

    object subTravelNote : subTopMenuScreen(
        route = "subTravelNote",
        icon = R.drawable.gps,
        title = "游记"
    )

    object subQuery : subTopMenuScreen(
        route = "subQuery",
        icon = R.drawable.find,
        title = "问答",
    )
}