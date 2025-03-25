package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.bottomNavi.BottomMenu

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Add(navController: NavController){
    Scaffold(
        topBar = {

        },
        drawerContent = {
        },
        content = {

        },
        bottomBar = {
            BottomMenu(navController);
        }
    )
}