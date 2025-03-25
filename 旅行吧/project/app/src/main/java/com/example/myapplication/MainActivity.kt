package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.MapView
import com.amap.api.maps2d.MapsInitializer.setApiKey
import com.amap.api.maps2d.model.LatLng
import com.amap.api.maps2d.model.MyLocationStyle
import com.amap.api.maps2d.model.PolylineOptions
import com.amap.apis.utils.core.api.AMapUtilCoreApi
import com.example.myapplication.Data.DataGet.getAttrationData
import com.example.myapplication.Data.DataGet.getLoginData
import com.example.myapplication.Data.DataGet.getNoteData
import com.example.myapplication.Data.DataGet.insertUserNoteData
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Note
import com.example.myapplication.functions.calDistance
import com.example.myapplication.functions.getTag
import com.example.myapplication.pages.Add
import com.example.myapplication.pages.ChangeBackgourndPicture
import com.example.myapplication.pages.ChangeYourAvatar
import com.example.myapplication.pages.Find
import com.example.myapplication.pages.MoreInformation
import com.example.myapplication.pages.NoteView
import com.example.myapplication.pages.Person_after
import com.example.myapplication.pages.Person_before
import com.example.myapplication.pages.Place
import com.example.myapplication.pages.Society
import com.example.myapplication.pages.Subscribe
import com.example.myapplication.pages.TravelView
import com.example.myapplication.pages.market
import com.example.myapplication.pages.register
import com.example.myapplication.pages.setting
import com.example.myapplication.start.Home
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.FindViewModel
import com.example.myapplication.viewmodel.ThemeViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
var selectedUris_avater by mutableStateOf<Uri>("null".toUri())
open class MainActivity : ComponentActivity() {
    private lateinit var mapView: MapView
    private lateinit var aMap: AMap;

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
    }

    private val selectImagesLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uris = mutableListOf<Uri>()
            result.data?.clipData?.let { clipData ->
                for (i in 0 until clipData.itemCount) {
                    clipData.getItemAt(i)?.uri?.let { uri ->
                        uris.add(uri)
                        persistUriPermission(uri, result.data!!.flags)  // 调用持久化权限的方法
                    }
                }
            } ?: result.data?.data?.let { uri ->
                uris.add(uri)
                persistUriPermission(uri, result.data!!.flags)  // 同样持久化单个文件的权限
            }
            selectedUris = selectedUris + uris
        }
        val persistedUriPermissions = this.contentResolver.persistedUriPermissions

    }
    private val selectImageLauncher_avatar = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri: Uri? = result.data?.clipData?.getItemAt(0)?.uri ?: result.data?.data
            uri?.let {
                persistUriPermission(it, result.data!!.flags)  // 调用持久化权限的方法
                selectedUris_avater = it
            }
        }
    }


    /**
     * 尝试持久化文件访问权限。
     * @param uri 文件的 URI。
     * @param intentFlags 从 Intent 获取的权限标志。
     */
    private fun persistUriPermission(uri: Uri, flags: Int) {

        try {
            val takeFlags: Int =
                flags and (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            contentResolver.takePersistableUriPermission(uri, takeFlags)
        } catch (e: SecurityException) {
            Log.e("SelectImages", "Failed to persist permission for URI: $uri", e)
        }

    }


    private val selectVideoLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedUris = selectedUris + it
        }
    }

    private var selectedUris by mutableStateOf<List<Uri>>(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setApiKey("43b1f22d69f93de4aa23bf02e711e5a7");
        AMapUtilCoreApi.setCollectInfoEnable(true);
        /*
        updatePrivacyShow(this, true, true);
        updatePrivacyAgree(this, true);

         */
        mapView = MapView(this).apply {
            onCreate(savedInstanceState) // 初始化地图并传递 savedInstanceState
        }
        aMap = mapView.getMap();
        setContent {
            MyApplicationTheme() {

                // A surface container using the 'background' color from the theme

                //val myNoteDatabaseHelper = MyDatabaseHelper
                //showNote(MyDatabaseHelper(this,"圆明园.db"))//获取笔记信息
                val navController = rememberNavController();
                val findViewModel: FindViewModel = viewModel()
                val themeViewModel: ThemeViewModel = viewModel();
                val context: Context = this;
                NavHost(navController, "travel") {
                    composable("travel") {
                        Home(navController, findViewModel, themeViewModel);
                    }
                    composable("add") {
                        Add(navController);
                    }
                    composable("person") {
                        val LoginDatabaseHelper: MyDatabaseHelper =
                            MyDatabaseHelper(LocalContext.current, "android.db");
                        if (getLoginData(LoginDatabaseHelper)[0].currentLogin == 0) {
                            Person_before(navController, themeViewModel)
                        } else {
                            Person_after(navController = navController, themeViewModel =themeViewModel,
                                selectedUris_avater
                            ) { selectImages_avatar() };
                        }

                    }
                    //下面是topnavi，上面是buttonnavi
                    composable("find") {
                        Find(navController,findViewModel, themeViewModel)
                    }
                    composable("subscribe") {
                        Subscribe(navController);
                    }
                    composable("place") {
                        Place(navController);
                    }
                    composable(
                        route = "NoteView/{topic}/{id}",
                    ) {
                        val topic = it.arguments?.getString("topic");
                        val id = it.arguments?.getString("id")?.toInt();
                        if (id != null && topic != null) {
                            NoteView(navController, topic, id)
                        }
                    }
                    composable(
                        route = "TravelView/{encodedTitle}/{encodedBody}/{encodedUri}",
                    ) {
                        val encodedTitle =
                            it.arguments?.getString("encodedTitle").let { encodedTitle ->
                                URLDecoder.decode(encodedTitle, StandardCharsets.UTF_8.toString())
                            }
                        val encodedBody =
                            it.arguments?.getString("encodedBody").let { encodedBody ->
                                URLDecoder.decode(encodedBody, StandardCharsets.UTF_8.toString())
                            }
                        val encodedUri = it.arguments?.getString("encodedUri").let { encodedUri ->
                            URLDecoder.decode(encodedUri, StandardCharsets.UTF_8.toString())
                        }
                        System.out.println("Decoder"+encodedTitle+" "+encodedBody+" "+encodedUri)
                        if (encodedTitle != null && encodedBody != null && encodedUri != null) {
                            TravelView(navController, encodedTitle, encodedBody, encodedUri)
                        }
                    }
                    composable("ChangeBackgroundPicture") {
                        ChangeBackgourndPicture()
                    }
                    composable("ChangeYourAvatar") {
                    }
                    composable("MoreInformation") {
                        MoreInformation()
                    }
                    composable("register") {
                        register(navController)
                    }
                    composable("Society") {
                        Society()
                    }
                    composable("market") {
                        market(navController, themeViewModel);
                    }
                    composable("setting") {
                        setting(navController);
                    }
                    composable("addNew") {
                        AddNew(
                            navController = navController,
                            imageUris = selectedUris,
                            onselectedImage = { selectImages() },
                            onselectedVideo = { selectVideo() },
                            onImageClick = { uri ->
                                val encodedUri = Uri.encode(uri.toString())
                                navController.navigate("PicDetail/$encodedUri")
                            },
                            onSave = { uris, title, body ->
                                saveToDatabase(uris, title, body, context)
                            },
                            ini = { initial() }
                        )
                    }
                    composable("PicDetail/{uri}") { backStackEntry ->
                        val context = LocalContext.current
                        val uriString = backStackEntry.arguments?.getString("uri")
                        val uri = uriString?.let { Uri.parse(it) }
                        uri?.let {
                            PicDetail(navController, uri = it, context) { deleteUri ->
                                selectedUris = selectedUris.filter { it != deleteUri }
                            }
                        }
                    }
                }//启动主页面
                //Log.d("1", getDistance(40.0091,116.28,39.9215,116.409).toString())
                //MapViewComposable(mapView)

            }
        }
    }

    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                // Explain to the user why we need the permission
                showDialog(
                    "Storage access is needed to read files.",
                    "OK",
                    {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            STORAGE_PERMISSION_CODE
                        )
                    }
                )
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    STORAGE_PERMISSION_CODE
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permissions are granted. Continue the action or workflow in your app.
                } else {
                    // Permissions are denied. Inform the user that the permissions are necessary.
                    showDialog(
                        "You need to grant storage permissions to use this feature.",
                        "OK",
                        {})
                }
                return
            }
            // Add more 'when' lines for other permission requests.
        }
    }

    private fun showDialog(message: String, positiveButton: String, onPositiveClick: () -> Unit) {
        // Implementation of a dialog to show the user a message with an action
    }

    override fun onStart() {
        super.onStart()
        checkAndRequestPermissions()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    private fun initial() {
        selectedUris = emptyList()
    }

    private fun selectImages() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        selectImagesLauncher.launch(intent)
    }
    private fun selectImages_avatar() {

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        selectImageLauncher_avatar.launch(intent)
    }

    private fun selectVideo() {
        selectVideoLauncher.launch("video/*")
    }

    ////////////////////////////////////////////////////////////////////////////
    @OptIn(DelicateCoroutinesApi::class)
    private fun saveToDatabase(uris: List<Uri>, title: String, body: String, context: Context) {
        var url = "";
        for (uri in uris) {
            url += uri.toString() + ";"
        }
        url += "!"
        url += title;
        url += "!";
        url += body
        var myDatabaseHelper = MyDatabaseHelper(context, "android.db")
        var currentUserID = getLoginData(myDatabaseHelper)[0].currentLogin;
        insertUserNoteData(myDatabaseHelper, currentUserID, url, LocalDateTime.now().toString())
        System.out.println(url)
    }
}

@Composable
fun showMapDemo() {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
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
                aMap.isMyLocationEnabled = true;
                /*
                val latLng = LatLng(39.906901, 116.397972)
                val marker = aMap.addMarker(
                    MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker")
                )

                 */

                val latLngs: MutableList<LatLng> = ArrayList()
                latLngs.add(LatLng(39.999391, 116.135972))
                latLngs.add(LatLng(39.898323, 116.057694))
                latLngs.add(LatLng(39.900430, 116.265061))
                latLngs.add(LatLng(39.955192, 116.140092))
                var polyline = aMap.addPolyline(
                    PolylineOptions()
                        .addAll(latLngs)
                        .width(10F)
                )


            }
        },
    )


}

fun showDialog(
    message: String,
    positiveButton: String,
    onPositiveClick: () -> Unit,
    context: Context
) {
    // 创建一个 AlertDialog 来向用户解释为什么需要某个权限
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton(positiveButton) { dialog, which ->
            onPositiveClick()  // 当用户点击对话框的按钮时，执行 onPositiveClick 中的代码，通常是重新请求权限
        }
        .setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()  // 关闭对话框
        }
        .create()
        .show()
}

fun showAtt(myDatabaseHelper: MyDatabaseHelper, select: String, context: Context) {
    val data = getAttrationData(myDatabaseHelper, select)//获取景点信息，只包括一个城市，点进去查看详情
    val attdistance = calDistance(data, context);//根据经纬度计算距离，没有实装高德距离
    /*
    Text(text = data.size.toString())
    //val data = mutableListOf<attraction>()
    LazyColumn(){
        items(data){
            Text(text = it.name);
            Text(text = it.address);
        }
    }
    */
    getTag(data, attdistance);//获取所有景点的tag信息，仅针对当前的城市
}

fun showNote(myDatabaseHelper: MyDatabaseHelper): List<Note> {
    return getNoteData(myDatabaseHelper)//notes是一个Mutable<Note>，存放所有笔记信息，仅针对当前一个景点，
    //Log.d("!",notes.size.toString());
    //val data = mutableListOf<attraction>()
    /* 类笔记页面演示
    LazyColumn(){
        items(notes){
            Text(
                text = it.title,
                style = TextStyle(
                    fontSize = 24.sp // 设置字体大小为24sp
                )
            )
            Text(text = it.textDetails);
        }
    }

     */
}


