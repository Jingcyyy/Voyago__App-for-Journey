package com.example.myapplication.uidesign

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.Data.Note
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.viewmodel.FindViewModel

@Composable
fun NoteRow(navController: NavController, findViewModel: FindViewModel) {
    var cnt = 0;
    val noteshowlist1 by findViewModel.notelist1.collectAsState();
    val noteshowlist2 by findViewModel.notelist2.collectAsState();
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            for (it in noteshowlist1) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        //.border(2.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id-1}")
                        },
                    elevation = 4.dp,
                ) {
                    if (checkisok(it)) {
                        Column(
                        ) {
                            AsyncImage(
                                model = it.cover,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth

                            )
                            Text(text = it.title,Modifier.padding(6.dp), fontSize = 14.sp);
                            Text(text = "#"+it.topic,Modifier.padding(6.dp), fontSize = 10.sp, color = Color.Gray);
                        }

                    }
                }
                //Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        //Spacer(modifier = Modifier.weight(0.1f))
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            for (it in noteshowlist2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        //.border(1.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id-1}")
                        },
                    elevation = 4.dp
                ) {
                    if (checkisok(it)) {
                        Column(
                        ) {
                            AsyncImage(
                                model = it.cover,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
//                            Text(text = it.title, fontSize = 16.sp);
//                            Text(text = "作者", fontSize = 16.sp);

                            Text(text = it.title,Modifier.padding(6.dp), fontSize = 14.sp);
                            Text(text = "# "+it.topic,Modifier.padding(6.dp), fontSize = 10.sp, color = Color.Gray);
                        }
                    }
                }
            }
        }

    }


}
@Composable
fun findNoteRow(navController: NavController, findViewModel: FindViewModel) {
    var cnt = 0;
    val noteshowlist1 by findViewModel.notefindlist1.collectAsState();
    val noteshowlist2 by findViewModel.notefindlist2.collectAsState();
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp),
    ) {
        //Spacer(modifier = Modifier.weight(0.05f))
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            for (it in noteshowlist1) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        //.border(2.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id-1}")
                        },
                    elevation = 4.dp,
                ) {
                    if (checkisok(it)) {
                        Column(
                        ) {
                            AsyncImage(
                                model = it.cover,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth

                            )
                            Text(text = it.title,Modifier.padding(6.dp), fontSize = 14.sp);
                            Text(text = "#"+it.topic,Modifier.padding(6.dp), fontSize = 10.sp, color = Color.Gray);
                        }

                    }
                }
                //Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        //Spacer(modifier = Modifier.weight(0.1f))
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            for (it in noteshowlist2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .clip(RoundedCornerShape(10.dp))
                        //.border(1.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id-1}")
                        },
                    elevation = 4.dp
                ) {
                    if (checkisok(it)) {
                        Column(
                        ) {
                            AsyncImage(
                                model = it.cover,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .fillMaxWidth(),
                                contentScale = ContentScale.FillWidth
                            )
//                            Text(text = it.title, fontSize = 16.sp);
//                            Text(text = "作者", fontSize = 16.sp);

                            Text(text = it.title,Modifier.padding(6.dp), fontSize = 14.sp);
                            Text(text = "# "+it.topic,Modifier.padding(6.dp), fontSize = 10.sp, color = Color.Gray);
                        }
                    }
                }
            }
        }

    }


}

//fun NoteRow(navController: NavController, findViewModel: FindViewModel) {
//    val noteshowlist1 by findViewModel.notefindlist1.collectAsState()
//    val noteshowlist2 by findViewModel.notefindlist2.collectAsState()
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Spacer(modifier = Modifier.weight(0.05f))
//
//        findNoteRow(navController, noteshowlist1, Modifier.weight(0.4f))
//        Spacer(modifier = Modifier.weight(0.1f))
//        findNoteRow(navController, noteshowlist2, Modifier.weight(0.4f))
//
//        Spacer(modifier = Modifier.weight(0.05f))
//    }
//}
//
//@Composable
//fun findNoteRow(navController: NavController, findViewModel: FindViewModel, modifier: Modifier = Modifier) {
//    val noteList by
//    LazyColumn(modifier = modifier) {
//        items(noteList) { it ->
//            if (checkisok(it)) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .border(1.dp, darkgreen)
//                        .clickable {
//                            navController.navigate("NoteView/${it.topic}/${it.id-1}")
//                        },
//                    elevation = 4.dp
//                ) {
//                    Column {
//                        AsyncImage(
//                            model = it.cover,
//                            contentDescription = null,
//                            modifier = Modifier
//                                .align(Alignment.CenterHorizontally)
//                                .fillMaxWidth(),
//                            contentScale = ContentScale.FillWidth
//                        )
//                        Text(text = it.title, fontSize = 16.sp)
//                        Text(text = "作者", fontSize = 16.sp)
//                    }
//                }
//                Spacer(modifier = Modifier.padding(16.dp))
//            }
//        }
//    }
//}


fun checkisok(note: Note): Boolean {
    if (note.author == null) return false;
    else if (note.id == null) return false;
    else if (note.collect == null) return false
    else if (note.time == null) return false;
    else return true
}