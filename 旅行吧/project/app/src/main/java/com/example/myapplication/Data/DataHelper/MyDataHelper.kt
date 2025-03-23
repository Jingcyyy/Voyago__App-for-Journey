package com.example.myapplication.Data.DataHelper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.compose.ui.platform.LocalContext
import java.io.FileOutputStream
import java.io.IOException
//需要获取的参数是一个context，如果在mainActivity就this，如果在别的地方就LocalContext.current,name是数据库的名字，我建的数据库和表同名，内部已经封装好了，直接用databaseName.db
class MyDatabaseHelper(
    val context: Context,
    val NAME:String,
) : SQLiteOpenHelper(context, NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "attraction.db"
    }
    init {
        // 确保在实例化helper时检查并复制数据库
        copyDatabase()
    }
    private fun copyDatabase() {
        val dbPath = context.getDatabasePath(NAME)
        if (!dbPath.exists()) {
            dbPath.parentFile?.mkdirs()
            try {
                val inputStream = context.assets.open(NAME)
                val outputStream = FileOutputStream(dbPath)

                inputStream.copyTo(outputStream)
                inputStream.close()
                outputStream.flush()
                outputStream.close()
            } catch (e: IOException) {
                throw RuntimeException("Error copying database from assets folder", e)
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    // 如果你需要处理数据库降级，可以重写 onDowngrade 方法
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

}
