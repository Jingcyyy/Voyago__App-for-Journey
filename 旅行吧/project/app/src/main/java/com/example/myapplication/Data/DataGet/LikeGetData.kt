package com.example.myapplication.Data.DataGet

import android.content.ContentValues
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Like

fun getLikeData(myDatabaseHelper: MyDatabaseHelper, userID: Int): List<Like> {
    var likes = mutableListOf<Like>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "likenote";
        val id = userID.toString()
        val selectQuery = "SELECT * FROM $tablename where userID = $id"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            while (moveToNext()) {
                val like = Like(
                    topic = getString(getColumnIndexOrThrow("topic")) ?: "null",
                    id = getInt(getColumnIndexOrThrow("id")) ?: 0,
                    userID = getInt(getColumnIndexOrThrow("userID")) ?: 0,
                )
                likes.add(like)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }

    return likes;
}

fun insertLikeData(myDatabaseHelper: MyDatabaseHelper, topic: String, id: Int, userID: Int) {
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = "likenote";
        val values = ContentValues().apply {
            put("topic", topic)
            put("id", id)
            put("userID", userID)
        }
        db.insertOrThrow(tablename, null, values)

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun deleteLikeData(myDatabaseHelper: MyDatabaseHelper, topic: String, id: Int, userID: Int) {
    try {
        myDatabaseHelper.readableDatabase.use { db ->
            val tableName = "likenote"
            db.delete(tableName, "topic = ? AND id = ? AND userID = ?", arrayOf(topic, id.toString(), userID.toString()))
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}