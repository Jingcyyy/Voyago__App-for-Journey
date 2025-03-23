package com.example.myapplication.start

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Note
import com.example.myapplication.R
import com.example.myapplication.bottomNavi.BottomMenu
import com.example.myapplication.functions.mytag
import com.example.myapplication.showNote
import com.example.myapplication.ui.theme.darkgreen
import com.example.myapplication.uidesign.NoteRow
import com.example.myapplication.uidesign.StAndFiAndDay
import com.example.myapplication.viewmodel.FindViewModel
import com.example.myapplication.viewmodel.ThemeViewModel


@SuppressLint(
    "UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter",
    "RememberReturnType", "StateFlowValueCalledInComposition"
)
@Composable
fun Home(
    navController:NavHostController,
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
                StAndFiAndDay(findViewModel,themeViewModel)
                Spacer(modifier = Modifier.padding(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(themeViewModel.ThemeColor.value.second)
                            .height(42.dp)
                            .fillMaxWidth(0.35f)
                            .align(Alignment.Center)
                    ) {
                        Text(
                            text = "相关推荐",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                    val configuration = LocalConfiguration.current
                    val screenWidth = configuration.screenWidthDp.dp
                    val notelist = findViewModel.attList.collectAsState();
                    val context = LocalContext.current;
                    // 创建一个 [InfiniteTransition] 实列用来管理子动画
                    FloatingActionButton(
                        onClick = {
                            var noteshowlist = mutableListOf<Note>();
                            var notestag = mutableListOf<String>()
                            for (note in notelist.value) {
                                notestag.add(mytag.alldata[note].name);
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
                            findViewModel.updateNoteList1(noteshowlist1);
                            findViewModel.updateNoteList2(noteshowlist2);
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
                }
                NoteRow(navController,findViewModel);
                Spacer(modifier = Modifier.height(68.dp))
            }

        },
        bottomBar = {
            BottomMenu(navController);
        }
    )
}
