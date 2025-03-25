package com.example.myapplication.Data.DataGet

import android.content.ContentValues
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.User
import com.example.myapplication.Data.UserNote

fun getUserNoteData(myDatabaseHelper: MyDatabaseHelper,userID:Int): List<UserNote> {
    var usernotes = mutableListOf<UserNote>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "note";
        val id = userID.toString()
        val selectQuery = "SELECT * FROM $tablename where userID = $id"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            while (moveToNext()) {
                val usernote = UserNote(
                    userID = getInt(getColumnIndexOrThrow("userID")) ?: 0,
                    time = getString(getColumnIndexOrThrow("time")) ?: "null",
                    text = getString(getColumnIndexOrThrow("text")) ?: "null",
                )
                usernotes.add(usernote)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }

    return usernotes;
}

fun updateUserNoteData(myDatabaseHelper: MyDatabaseHelper, userID: Int, text: String, time: String) {
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "note";
        val values = ContentValues().apply {
            put("text", text)
            put("time", time)
            // 添加其他需要更新的字段
        }
        val selection = "id = ?"
        val selectionArgs = arrayOf(userID.toString())
        db.update(tablename, values, selection, selectionArgs)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun insertUserNoteData(myDatabaseHelper: MyDatabaseHelper, userID: Int, text: String, time: String) {
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "note";
        val values = ContentValues().apply {
            put("userID", userID)
            put("time", time)
            put("text", text)
        }
        db.insertOrThrow(tablename, null, values)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}