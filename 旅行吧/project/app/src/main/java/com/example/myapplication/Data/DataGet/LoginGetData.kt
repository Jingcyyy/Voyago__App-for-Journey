package com.example.myapplication.Data.DataGet

import android.content.ContentValues
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Login

fun getLoginData(myDatabaseHelper: MyDatabaseHelper): MutableList<Login> {
    var Logins = mutableListOf<Login>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "Login";
        val selectQuery = "SELECT * FROM $tablename"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            while (moveToNext()) {
                val Login = Login(
                    currentLogin = getInt(getColumnIndexOrThrow("currentLogin")) ?: 0,

                )
                Logins.add(Login)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }
    if(Logins.size == 0){
        insertLoginData(myDatabaseHelper,0);
        return mutableListOf(Login(0))
    }
    return Logins;
}
fun insertLoginData(myDatabaseHelper: MyDatabaseHelper,currentLogin:Int) {
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "Login";
        val values = ContentValues().apply {
            put("currentLogin", currentLogin)
        }
        db.insertOrThrow(tablename, null, values)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun deleteLoginData(myDatabaseHelper: MyDatabaseHelper){
    try {
        val db = myDatabaseHelper.writableDatabase;
        val tablename = "Login";
        db.delete(tablename,null,null)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}