package com.example.myapplication.functions

import android.os.Bundle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.amap.api.maps2d.AMapOptions
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.model.CameraPosition
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MarkerOptions
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.maps2d.model.PolylineOptions
import com.example.myapplication.Data.attraction
import com.example.myapplication.uidesign.prefer
import com.example.myapplication.viewmodel.FindViewModel
import com.example.myapplication.viewmodel.topiclist
import kotlinx.coroutines.launch
import kotlin.random.Random


data class datatag(
    var alldata: List<attraction>,
    var alldis: MutableList<MutableList<Double>>,
    var TagsetList: MutableList<String>,
    var tagList: MutableList<String>,
    var TagsetMap: MutableMap<String, MutableList<Int>>,//tagmap是一个映射，获取了每个tag对应的景点的编号
    var tagMap: MutableMap<String, MutableList<Int>>,
    var DIS: MutableList<MutableList<Double>>,
    var Tagtag: MutableMap<String, MutableSet<String>>,
)

lateinit var mytag: datatag;
var minDis = 1000000.0;
var ansPath: path = path(mutableListOf(), 0.0);
var clicklist = MutableList(200) { 0 }

fun getTag(
    datas: List<attraction>,
    dis: MutableList<MutableList<Double>>,
) {
    val Tagset = LinkedHashSet<String>()
    val tagset = LinkedHashSet<String>()//tag集合
    val tagmap = mutableMapOf<String, MutableList<Int>>()//每个tag对应的景点编号
    val Tagmap = mutableMapOf<String, MutableList<Int>>()//每个Tag对应的景点编号
    var Tagtag = mutableMapOf<String, MutableSet<String>>();
    for (data in datas) {
        val tags = data.tag
        val split = tags.split(";")
        Tagset.add(split[0])
        // 确保 tagmap 中每个 split 都有一个 MutableList，如果没有则初始化一个空的 MutableList
        if (Tagmap[split[0]] == null) {
            Tagmap[split[0]] = mutableListOf()
        }
        Tagmap[split[0]]?.add(data.num)
        tagset.add(split[1])
        // 确保 tagmap 中每个 split 都有一个 MutableList，如果没有则初始化一个空的 MutableList
        if (tagmap[split[1]] == null) {
            tagmap[split[1]] = mutableListOf()
        }
        tagmap[split[1]]?.add(data.num)
        if (Tagtag[split[0]] == null) {
            Tagtag[split[0]] = mutableSetOf();
        }
        Tagtag[split[0]]?.add(split[1]);

    }
    mytag = datatag(
        datas, dis, Tagset.toMutableList(), tagset.toMutableList(), Tagmap, tagmap, dis, Tagtag
    )/*
    for (prefer in ansPath.order) {
        System.out.println(datas[prefer].name + " " + datas[prefer].tag);
    }
     */
}

//find(0, 0, size, path(mutableListOf(), 0.0))
fun find(
    num: Int, size: Int, path: path, prefer: List<String>
) {//num是当前的编号，cnt是目前的个数，size是最大的个数，path是当前的数据记录
    if (num == size) {
        if (minDis > path.cost) {
            minDis = path.cost
            ansPath = path;
            System.out.println(ansPath)
        }
    } else {
        var nowLeveltag = prefer;
        var nowLevelatts = mytag.tagMap;
        var nowdis = mytag.DIS;
        var tempList = nowLevelatts[nowLeveltag[num]]!!;
        for (att in tempList.shuffled()) {
            val newPath = path.copy(order = ArrayList(path.order), cost = path.cost)
            if ((newPath.order.find { it == att }) != null) {
                continue;
            }
            if (newPath.order.isNotEmpty()) {
                val pre = newPath.order.last()
                newPath.cost += nowdis[pre][att]
            }
            newPath.order.add(att)
            find(num + 1, size, newPath, prefer)
        }
    }
}

@Composable
fun startfind_onlysize(size: Int, findViewModel: FindViewModel) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    var temPrefer = mutableListOf<String>();
    var mytagList = mytag.tagList;
    for (tag in mytagList) {
        if (prefer[tag] == true) {
            temPrefer.add(tag);
        }
    }
    val st by findViewModel.attstart.collectAsState();
    val fi by findViewModel.attfinal.collectAsState();
    var tempSize = size;
    if (st in topiclist) {
        tempSize--;
    }
    if (fi in topiclist) {
        tempSize--;
    }
    if (tempSize < 0) {
        showDialog.value = true
    }
    else{
        if (temPrefer.size < tempSize) {
            while (temPrefer.size < tempSize) {
                val randomInt = Random.nextInt(0, mytagList.size);
                temPrefer.add(mytagList[randomInt]);
            }
        } else if (temPrefer.size > tempSize) {
            var tempsize = temPrefer.size;
            for (i in 0 until tempSize) {
                val randomInt = Random.nextInt(0, tempsize);
                temPrefer.add(temPrefer[randomInt]);
            }
            temPrefer.subList(0, tempsize).clear();
        }
        minDis = 1000000.0;
        //find(0, size, path(mutableListOf<Int>(), 0.0), temPrefer.shuffled());
        /*
        minDis =1000000.0;
        find(0,size,path(mutableListOf<Int>(),0.0),temPrefer.shuffled());

         */
        var tempnote = mutableListOf<String>()
        val scope = rememberCoroutineScope();
        var x = findViewModel.setisok.collectAsState()
        if (clicklist[x.value] == 0) {
            LaunchedEffect(key1 = x.value) {
                scope.launch {
                    find(0, tempSize, path(mutableListOf<Int>(), 0.0), temPrefer.shuffled());
                }
                var checkst = 0;
                if(st in topiclist){
                    checkst =1;
                }
                var checkfi = 0;
                if(fi in topiclist){
                    checkfi =1
                }
                findViewModel.updateAtt(ansPath,checkst,checkfi)
                clicklist[x.value] = 1;
            }
        }
    }
    if (showDialog.value) {
        AlertDialog(onDismissRequest = { showDialog.value = false },
            title = {
                Text(text = "提示")
            },
            text = { Text(text = "出现错误，预计地点少于2个") },
            confirmButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text(text = "确认")
                }
            }
        )
    }


}
//prefer存储了所有被选中的tag


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun generateRoute(findViewModel: FindViewModel) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
    ) {
        val notelist by findViewModel.attList.collectAsState();
        notelist.forEach { title ->
            var selected by remember { mutableStateOf(false) }
            val leadingIcon: @Composable () -> Unit = { Icon(Icons.Default.Check, null) }
            FilterChip(
                selected,
                onClick = { selected = !selected },
                label = { Text(mytag.alldata[title].name) },
                leadingIcon = if (selected) leadingIcon else null
            )
            if (selected) {

            }


        }
    }

}

var LatLngMap: MutableMap<String, Pair<Double, Double>> = mutableMapOf(
    "北京" to Pair(116.41667, 39.91667),
    "上海" to Pair(121.43333, 34.50000),
    "成都" to Pair(104.06667, 30.66667),
    "广州" to Pair(113.23333, 23.16667),
    "武汉" to Pair(114.31667, 30.51667),
    "西安" to Pair(108.95000, 34.26667),
)

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun generateMap(
    place: String, findViewModel: FindViewModel
) {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize().aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        val orders by findViewModel.attList.collectAsState();
        AndroidView(
            factory = { context ->
                val centerBJPoint = LatLngMap[place]?.let {
                    LatLngMap[place]?.let { it1 ->
                        LatLng(
                            it.second,
                            it1.first
                        )
                    }
                }
                val mapOptions = AMapOptions()
                mapOptions.camera(CameraPosition(centerBJPoint, 10f, 0f, 0f))
                MapView(context, mapOptions).apply {
                    this.onCreate(Bundle());
                    var aMap = this.map;
                    var myLocationStyle = MyLocationStyle()
                    //定位一次，且将视角移动到地图中心点。
                    myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)
                    // 设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
                    // 设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位蓝点的概念，但是会持续回调位置信息
                    myLocationStyle.showMyLocation(true)
                    myLocationStyle.anchor(0.1f, 0.1f)
                    // 设置定位蓝点的Style
                    aMap.setMyLocationStyle(myLocationStyle);
                    aMap.getUiSettings().isMyLocationButtonEnabled = true
                    aMap.isMyLocationEnabled = true;/*
                val latLng = LatLng(39.906901, 116.397972)
                val marker = aMap.addMarker(
                    MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker")
                )

                 */
                    /*
                    val latLngs: MutableList<LatLng> = ArrayList()

                    for (order in orders) {
                        var nowxLatlng = LatLng(
                            mytag.alldata[order].location_lat.toDouble(),
                            mytag.alldata[order].location_lng.toDouble()
                        )
                        latLngs.add(
                            nowxLatlng
                        )
                        val marker = aMap.addMarker(
                            MarkerOptions().position(nowxLatlng).title(mytag.alldata[order].name)
                        )
                    }
                    var polyline = aMap.addPolyline(
                        PolylineOptions().addAll(latLngs)
                    )

                     */
                }
            },
            update = { mapView ->
                mapView.map.clear() // 清除所有标记以避免重叠
                val aMap = mapView.map
                val latLngs: MutableList<LatLng> = ArrayList()

                for (order in orders) {
                    val nowxLatlng = LatLng(
                        mytag.alldata[order].location_lat.toDouble(),
                        mytag.alldata[order].location_lng.toDouble()
                    )
                    latLngs.add(nowxLatlng)
                    aMap.addMarker(
                        MarkerOptions().position(nowxLatlng).title(mytag.alldata[order].name)
                    )
                }
                aMap.addPolyline(PolylineOptions().addAll(latLngs))
            },
            modifier = Modifier.aspectRatio(1f)
        )
    }
}
