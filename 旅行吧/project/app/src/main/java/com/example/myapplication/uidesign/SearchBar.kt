package com.example.myapplication.uidesign

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar() {
    // 状态记忆，用于保存搜索栏中的文本
    val searchText = remember { mutableStateOf("") }
    Card(
        modifier = Modifier
            .fillMaxWidth() // 让Card填满宽度
            .padding(8.dp), // 添加一些外部间距
        elevation = 4.dp // 设置阴影高度
    ) {
        OutlinedTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            modifier = Modifier
                .fillMaxWidth() // 让TextField填满Card的宽度
                .padding(8.dp), // 添加一些内部间距
            placeholder = { Text("Search here...") }, // 占位符
            singleLine = true, // 单行模式
            shape = MaterialTheme.shapes.small // TextField的形状
        )
    }
}


