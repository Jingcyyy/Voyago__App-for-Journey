package com.example.myapplication.uidesign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.functions.mytag
import com.example.myapplication.ui.theme.darkgreen

var prefer: MutableMap<String, Boolean> = mutableMapOf();

/*
@OptIn(ExperimentalLayoutApi::class)
@Composable

fun Tags() {
    val filters = mytag.TagsetList
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        filters.forEach { title ->
            var selected by remember { mutableStateOf(false) }
            val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }

            FilterChip(
                selected,
                onClick = { selected = !selected },
                label = { Text(title) },
                leadingIcon = if (selected) leadingIcon else null
            )
            if (selected) {
                tags(title);
            }
        }
    }
}

 */
@OptIn(ExperimentalLayoutApi::class)
@Composable

fun Tags() {
    val filters = mytag.TagsetList
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(darkgreen)
                .width(50.dp)
                .height(80.dp)
                .align(Alignment.CenterVertically),
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(text = "T", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 2.dp, top = 3.dp))
                Text(text = "A", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 2.dp))
                Text(text = "G", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold,modifier = Modifier.padding(start = 2.dp, bottom = 3.dp))
            }

        }
        Spacer(modifier = Modifier.padding(4.dp))
        Column {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                filters.forEach { title ->
                    var selected by remember { mutableStateOf(false) }
                    val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }

                    FilterChip(
                        selected,
                        onClick = { selected = !selected },
                        label = { Text(title) },
                        leadingIcon = if (selected) leadingIcon else null
                    )
                    if (selected) {
                        tags(title);
                    }


                }
            }


        }

    }


}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun tags(title: String) {
    val filters = mytag.Tagtag[title];
    var taglist = filters?.toList();
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        if (filters != null) {
            if (taglist != null) {
                taglist.forEach { title ->
                    var selected by remember { mutableStateOf(false) }
                    val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
                    FilterChip(
                        selected,
                        onClick = {
                            selected = !selected;
                            prefer[title] = selected;
                        },
                        label = { Text(title) },
                        leadingIcon = if (selected) leadingIcon else null,
                    )
                }
            }
        }
    }
}