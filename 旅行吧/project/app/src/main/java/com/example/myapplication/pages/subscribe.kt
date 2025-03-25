package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.uidesign.SearchBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Subscribe(navController: NavController){
    Scaffold(
        drawerContent = {
        },
        content = {
            Column {
                SearchBar();
            }

        },
        bottomBar = {
            BottomMenu(navController);
        }
    )
}