package com.example.myapplication.pages

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Note
import com.example.myapplication.R
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.functions.mytag
import com.example.myapplication.showNote
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.uidesign.findNoteRow
import com.example.myapplication.viewmodel.FindViewModel
import com.example.myapplication.viewmodel.RecommendViewModel
import com.example.myapplication.viewmodel.ThemeViewModel


@OptIn(ExperimentalMaterialApi::class)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun Find(
    navController: NavController,
    findViewModel: FindViewModel,
    themeViewModel: ThemeViewModel
) {
    Scaffold(
        topBar = {
        },
        content = {

            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(Color(0xFFF6FAEB))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.flowerbg),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Text(text = "相关推荐",
                        Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        letterSpacing = 1.sp,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Box(Modifier.height(20.dp).align(Alignment.CenterHorizontally)) {
                    Text(text = "点击底部按钮，发现更多美好~",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(top = 6.dp),
                        fontSize = 10.sp,
                        color = Color.Gray
                    )
                    //Spacer(modifier = Modifier.padding(10.dp))

                }
                findNoteRow(navController,findViewModel);
            }

        },
        bottomBar = {
            Column {
                val configuration = LocalConfiguration.current
                val screenWidth = configuration.screenWidthDp.dp
                findViewModel.updateFind()
                val notelist = findViewModel.findList.collectAsState();
                val context = LocalContext.current;
                FloatingActionButton(
                    onClick = {
                        findViewModel.updateFind()
                        var notestag = mutableListOf<String>()
                        for (note in notelist.value) {
                            notestag.add(mytag.alldata[note].name);
                            System.out.println(mytag.alldata[note].name)
                        }
                        var noteshowlist1 = mutableListOf<Note>();
                        var noteshowlist2 = mutableListOf<Note>();
                        for (notetag in notestag) {
                            if (notetag != "") {
                                Log.d("!", notetag)
                                val currentNote = showNote(MyDatabaseHelper(context, notetag + ".db"))
                                val randomNote = currentNote.shuffled();

                                if (currentNote.size != 0) {
                                    noteshowlist1.add(randomNote[3])
                                    noteshowlist2.add(randomNote[4])
                                }
                            }
                        }
                        findViewModel.updatefindNoteList1(noteshowlist1);
                        findViewModel.updatefindNoteList2(noteshowlist2);
                    },
                    modifier = Modifier
                        .offset(x = screenWidth * 3 / 4),
                    backgroundColor = Color.White
                ) {
                    Icon(
                        painterResource(id = R.drawable.baseline_autorenew_24),
                        contentDescription = null,
                        tint = darkgreen,
                    )
                }
                Spacer(modifier = Modifier.padding(12.dp))
                BottomMenu(navController);
            }
        }
    )
}


@Composable
fun find(findViewModel: FindViewModel = viewModel()) {
    /*
    var _lists = findViewModel.notelist.collectAsState();
    for(i in _lists.value){
        Text(text = i.first.toString()+" "+i.second.toString());
    }
    Button(onClick = {
        findViewModel.refresh();
    }) {
        Text(text = "刷新")
    }

     */
}

@Composable
fun findnote(navController: NavController, recommendViewModel: RecommendViewModel) {
    //var notestag = notelist.value.split(';');
    var noteshowlist1 = mutableListOf<Note>();
    var noteshowlist2 = mutableListOf<Note>();
    var notestag = listOf<String>("颐和园", "故宫", "玉渊潭公园")
    var noteshowlist = recommendViewModel.notelist.collectAsState();
    recommendViewModel.refresh(notestag, LocalContext.current)
    for (i in 0..noteshowlist.value.size - 1) {
        val currentNote =
            showNote(MyDatabaseHelper(LocalContext.current, noteshowlist.value[i].first + ".db"))
        if (i % 2 == 0) {
            noteshowlist1.add(currentNote[noteshowlist.value[i].second]);
        } else {
            noteshowlist2.add(currentNote[noteshowlist.value[i].second]);
        }
    }
    /*
    for (notetag in notestag) {
        if (notetag != "") {
            Log.d("!", notetag)
            val currentNote = showNote(MyDatabaseHelper(LocalContext.current, notetag + ".db"))
            val randomNote = currentNote.shuffled();

            if (currentNote.size != 0) {
                noteshowlist1.add(randomNote[3])
                noteshowlist2.add(randomNote[4])
            }
        }
    }

     */
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Spacer(modifier = Modifier.weight(0.05f))
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            for (it in noteshowlist1) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id}")
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
                            Text(text = it.title, fontSize = 16.sp);
                            Text(text = "author", fontSize = 16.sp);
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        Spacer(modifier = Modifier.weight(0.1f))
        Column(
            modifier = Modifier.weight(0.4f)
        ) {
            for (it in noteshowlist2) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, darkgreen)
                        .clickable {
                            navController.navigate("NoteView/${it.topic}/${it.id}")
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
                            Text(text = it.title, fontSize = 16.sp);
                            Text(text = "author", fontSize = 16.sp);
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        Spacer(modifier = Modifier.weight(0.05f))

    }


    /*
    for(it in noteshowlist2){
        Card(
            modifier = Modifier.fillMaxWidth(0.4f)
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
                    Text(text = it.title + "title",);
                    Text(text = "author");
                }

            }

        }


    }*/


}

fun checkisok(note: Note): Boolean {
    if (note.author == null) return false;
    else if (note.id == null) return false;
    else if (note.collect == null) return false
    else if (note.time == null) return false;
    else return true
}