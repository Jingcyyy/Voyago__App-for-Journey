package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.Note
import com.example.myapplication.functions.ansPath
import com.example.myapplication.functions.path
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

val topiclist: List<String> = listOf(
    "颐和园",
    "故宫",
    "八达岭长城",
    "天坛公园",
    "天安门广场",
    "恭王府",
    "奥林匹克森林公园",
    "明十三陵",
    "慕田峪长城",
    "石林峡",
    "什刹海",
    "圆明园",
    "玉渊潭公园",
    "北京动物园",
    "北海公园",
    "北京野生动物园",
    "国家植物园",
    "北京欢乐谷",
    "十渡",
    "世界公园",
    "景山公园",
    "香山公园",
    "八大处公园",
    "大运河森林公园",
    "雁栖湖",
    "陶然亭",
    "北京国际鲜花港",
    "凤凰岭",
    "朝阳公园",
    "北京园博园",
    "北宫国家森林公园",
    "首都博物馆",
    "世界花卉大观园",
    "黄花城水长城",
    "红螺寺",
    "卢沟桥",
    "百里画廊",
    "北京海洋馆",
    "奥林匹克公园",
    "中央电视台",
    "青龙峡",
    "龙潭公园",
    "奥林匹克水上公园",
    "金海湖",
    "元大都遗址公园",
    "紫竹院公园",
    "龙庆峡",
    "蓝调薰衣草庄园",
    "地坛公园",
    "野鸭湖湿地公园",
    "日坛公园",
    "地质公园",
    "中国科技馆",
    "孔庙和国子监博物馆",
    "北京圣莲山",
    "银山塔林",
    "周口店遗址博物馆",
    "汽车博物馆",
    "中国人民抗日战争纪念馆",
    "中山公园"
)

//我希望为每一个地点都编号一个，并且我拥有每个景点的notelist的编号
class FindViewModel : ViewModel() {
    private val _attnum = MutableStateFlow("");
    val attnum: StateFlow<String> = _attnum;
    fun updateNum(num: String) {
        viewModelScope.launch {
            _attnum.value = num;
        }
    }

    private val _attstart = MutableStateFlow("");
    val attstart: StateFlow<String> = _attstart;
    fun updateStart(start: String) {
        viewModelScope.launch {
            _attstart.value = start;
        }
    }

    private val _attfinal = MutableStateFlow("");
    val attfinal: StateFlow<String> = _attfinal;
    fun updateFinal(final: String) {
        viewModelScope.launch {
            _attfinal.value = final;
        }
    }

    private val _attList = MutableStateFlow(mutableListOf<Int>())
    val attList: StateFlow<MutableList<Int>> = _attList;
    fun updateAtt(anspath: path,st:Int,fi:Int) {
        viewModelScope.launch {
            var tempnote = mutableListOf<Int>()
            if(st == 1){
                var pl = topiclist.indexOf(_attstart.value);
                System.out.println("起点"+_attstart.value)
                tempnote.add(pl)
            }
            for (att in ansPath.order) {
                tempnote.add(att);
            }
            if(fi == 1){
                var pl = topiclist.indexOf(_attfinal.value);
                tempnote.add(pl)
            }
            _attList.value = tempnote;
            System.out.println("地图是"+tempnote)
        }
    }

    private val _findList = MutableStateFlow(mutableListOf<Int>())
    val findList: StateFlow<MutableList<Int>> = _findList;
    fun updateFind() {
        viewModelScope.launch {
            var tempfind = mutableListOf<Int>()
            for(i in 1..6){
                tempfind+= Random.nextInt(1,61)
            }
            _findList.value = tempfind
        }
    }

    private val _setisok = MutableStateFlow(0)
    val setisok: StateFlow<Int> = _setisok;
    fun updateok() {
        viewModelScope.launch {
            System.out.println(_setisok.value)
            _setisok.value++
        }
    }

    /*
    var noteshowlist1 = mutableListOf<Note>();
    var noteshowlist2 = mutableListOf<Note>();
    var notestag = mutableListOf<String>()
    val notelist = findViewModel.attList.collectAsState();
    for (note in notelist.value) {
        notestag.add(mytag.alldata[note].name);
    }
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
    private val _notelist1 = MutableStateFlow(mutableListOf<Note>())
    val notelist1: StateFlow<MutableList<Note>> = _notelist1;
    fun updateNoteList1(List: MutableList<Note>) {
        viewModelScope.launch {
            _notelist1.value = mutableListOf<Note>()
            _notelist1.value = List;
        }
    }

    private val _notelist2 = MutableStateFlow(mutableListOf<Note>())
    val notelist2: StateFlow<MutableList<Note>> = _notelist2;
    fun updateNoteList2(List: MutableList<Note>) {
        viewModelScope.launch {
            _notelist2.value = mutableListOf<Note>()
            _notelist2.value = List;

        }
    }


    private val _notefindlist1 = MutableStateFlow(mutableListOf<Note>())
    val notefindlist1: StateFlow<MutableList<Note>> = _notefindlist1;
    fun updatefindNoteList1(List: MutableList<Note>) {
        viewModelScope.launch {
            _notefindlist1.value = mutableListOf<Note>()
            _notefindlist1.value = List;
        }
    }

    private val _notefindlist2 = MutableStateFlow(mutableListOf<Note>())
    val notefindlist2: StateFlow<MutableList<Note>> = _notefindlist2;
    fun updatefindNoteList2(List: MutableList<Note>) {
        viewModelScope.launch {
            _notefindlist2.value = mutableListOf<Note>()
            _notefindlist2.value = List;

        }
    }
}
