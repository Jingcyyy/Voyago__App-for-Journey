package com.example.myapplication.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.AttNote
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.showNote
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class RecommendViewModel : ViewModel() {
    private val _notelist = MutableStateFlow(mutableListOf<Pair<String, Int>>())
    val notelist: StateFlow<MutableList<Pair<String, Int>>> = _notelist;
    fun refresh(recommendList:List<String>,context: Context){//存放的是我的笔记信息
        viewModelScope.launch {
            _notelist.value = mutableListOf()
            for(recomend in recommendList){
                val currentNote = showNote(MyDatabaseHelper(context, recomend + ".db"))
                val numInt1 = AttNote[recomend]?.let { Random.nextInt(3, it-1) }
                val numInt2 = AttNote[recomend]?.let { Random.nextInt(3, it-1) }
                _notelist.value.add(Pair(recomend,numInt1) as Pair<String, Int>);
                _notelist.value.add(Pair(recomend,numInt2) as Pair<String, Int>);
            }
        }
    }
}