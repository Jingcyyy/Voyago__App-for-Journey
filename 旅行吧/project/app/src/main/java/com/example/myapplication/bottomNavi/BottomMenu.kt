package com.example.myapplication.bottomNavi

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomMenu(
    navController: NavController
) {
    val menuItems = mutableListOf<BottomMenuScreen>(
        BottomMenuScreen.find,
        BottomMenuScreen.home,
        BottomMenuScreen.person,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState();
    val currentRoute = navBackStackEntry?.destination?.route;
    BottomNavigation(
        contentColor = Color.White,
        backgroundColor = Color(0xFFF6FAEB),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)

            //.wrapContentSize(Alignment.Center)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState();
        val currentRoute = navBackStackEntry?.destination?.route;
        menuItems.forEach {
            BottomNavigationItem(
                modifier = Modifier.align(Alignment.CenterVertically),
                selected = currentRoute == it.route,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.LightGray,
                label = { Text(text = it.title, fontWeight = FontWeight.Bold, fontSize = 14.sp, letterSpacing = 2.sp) },
                onClick = {
                    navController.navigate(it.route);
                },
                icon = {
                    Icon(painterResource(id = it.icon), contentDescription = it.title,tint = Color.Unspecified
                        ,
                        modifier = Modifier.padding(3.dp))
                })
        }
    }
}
