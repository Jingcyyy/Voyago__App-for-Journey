package com.example.myapplication.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.uidesign.SearchBar
import com.example.myapplication.uidesign.Tags
import com.example.myapplication.uidesign.prefer

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Place(navController: NavController){
    Scaffold(

        drawerContent = {
        },
        content = {
            Column {
                SearchBar();
                Tags();
                Button(onClick = {
                    System.out.println(prefer);
                }) {
                }
            }

        },
        bottomBar = {
            BottomMenu(navController);
        }
    )
}