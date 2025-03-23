package com.example.myapplication.Data.DataGet

import android.content.ContentValues
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Note
import com.example.myapplication.Data.User

fun getUserData(myDatabaseHelper: MyDatabaseHelper): List<User> {
    var users = mutableListOf<User>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "user";
        val selectQuery = "SELECT * FROM $tablename"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            while (moveToNext()) {
                val user = User(
                    userID = getInt(getColumnIndexOrThrow("userID")) ?: 0,
                    userName = getString(getColumnIndexOrThrow("userName")) ?: "null",
                    pwd = getString(getColumnIndexOrThrow("pwd")) ?: "null",
                    avatar = getString(getColumnIndexOrThrow("avatar")) ?: "null",
                )
                users.add(user)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }

    return users;
}

fun updateUserData(myDatabaseHelper: MyDatabaseHelper, userID: Int,avatar:String) {
    try {
        val db = myDatabaseHelper.writableDatabase;
        val tablename = "user";
        val values = ContentValues().apply {
            put("avatar",avatar)
            // 添加其他需要更新的字段
        }
        val selection = "userID = ?"
        val selectionArgs = arrayOf(userID.toString())
        System.out.println("alreay")
        db.update(tablename, values, selection, selectionArgs)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun insertUserData(myDatabaseHelper: MyDatabaseHelper, userID: Int, userName: String, pwd: String,avatar:String) {
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "user";
        val values = ContentValues().apply {
            put("userID", userID)
            put("userName", userName)
            put("pwd", pwd)
            put("avatar",avatar)
        }
        val selection = "id = ?"
        val selectionArgs = arrayOf(userID.toString())
        db.insertOrThrow(tablename, null, values)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}