package com.example.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.darkgreen

@Composable
fun addlaunch(navController: NavController) {
    Box(modifier = Modifier.fillMaxWidth().padding(20.dp)){
        FloatingActionButton(onClick = {
            navController.navigate("addNew")
        }, modifier = Modifier.align(Alignment.BottomEnd), backgroundColor = darkgreen) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null,tint = Color.White)
        }
    }

}