package com.example.myapplication.topNavi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenu(
    navController: NavController,
    modifier: Modifier
) {
    val menuItems = mutableListOf<subTopMenuScreen>(
        subTopMenuScreen.subNote,
        subTopMenuScreen.subTravelNote,
        subTopMenuScreen.subQuery
    )
    TopAppBar(
        title = { Text(text = "") },
        navigationIcon = {
            LazyRow(
            ) {
                items(menuItems){
                    IconButton(
                        onClick = {
                            navController.navigate(it.route)
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Column {
                            Icon(painterResource(id = it.icon), contentDescription = it.title)
                            Text(text = it.title, modifier = Modifier.fillMaxHeight())
                        }

                    }
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}
