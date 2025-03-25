package com.example.myapplication.Data.DataGet

import android.util.Log
import com.example.myapplication.Data.DataHelper.MyDatabaseHelper
import com.example.myapplication.Data.Note
import com.example.myapplication.Data.attraction

fun getAttrationData(myDatabaseHelper: MyDatabaseHelper,select:String): List<attraction> {
    var data = mutableListOf<attraction>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        var place = "${select+"市"}"
        val selectQuery = "SELECT * FROM attraction where city = ${'"'+place+'"'}"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            var cnt = 0;
            while (moveToNext()) {
                val attraction = attraction(
                    num = cnt++,
                    name = getString(getColumnIndexOrThrow("name")) ?: "null",
                    province = getString(getColumnIndexOrThrow("province")) ?: "null",
                    city = getString(getColumnIndexOrThrow("city")) ?: "null",
                    area = getString(getColumnIndexOrThrow("area")) ?: "null",
                    address = getString(getColumnIndexOrThrow("address")) ?: "null",
                    tag = getString(getColumnIndexOrThrow("tag")) ?: "null",
                    type = getString(getColumnIndexOrThrow("type")) ?: "null",
                    telephone = getString(getColumnIndexOrThrow("telephone")) ?: "null",
                    overall_rating = getString(getColumnIndexOrThrow("overall_rating")) ?: "null",
                    comment_num = getString(getColumnIndexOrThrow("comment_num")) ?: "null",
                    location_lat = getString(getColumnIndexOrThrow("location_lat")) ?: "null",
                    location_lng = getString(getColumnIndexOrThrow("location_lng")) ?: "null",
                    navi_location_lat = getString(getColumnIndexOrThrow("navi_location_lat"))
                        ?: "null",
                    navi_location_lng = getString(getColumnIndexOrThrow("navi_location_lng"))
                        ?: "null"
                )
                data.add(attraction)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }

    return data;
}

fun getNoteData(myDatabaseHelper: MyDatabaseHelper): List<Note> {
    var notes = mutableListOf<Note>();
    try {
        val db = myDatabaseHelper.readableDatabase;
        val tablename = myDatabaseHelper.NAME.split('.')[0];
        val selectQuery = "SELECT * FROM $tablename"
        val cursor = db.rawQuery(selectQuery, null);
        with(cursor) {
            while (moveToNext()) {
                val note = Note(
                    id = getInt(getColumnIndexOrThrow("id")) ?: 0,
                    topic = getString(getColumnIndexOrThrow("topic")) ?: "null",
                    title = getString(getColumnIndexOrThrow("title")) ?: "null",
                    cover = getString(getColumnIndexOrThrow("cover")) ?: "null",
                    author = getString(getColumnIndexOrThrow("author")) ?: "null",
                    profile = getString(getColumnIndexOrThrow("profile")) ?: "null",
                    name = getString(getColumnIndexOrThrow("name")) ?: "null",
                    like = getString(getColumnIndexOrThrow("like")) ?: "null",
                    noteDetailsLink = getString(getColumnIndexOrThrow("noteDetailsLink")) ?: "null",
                    textDetails = getString(getColumnIndexOrThrow("textDetails")) ?: "null",
                    picture = getString(getColumnIndexOrThrow("picture")) ?: "null",
                    time = getString(getColumnIndexOrThrow("time")) ?: "null",
                    likes = getString(getColumnIndexOrThrow("likes")) ?: "null",
                    collect = getString(getColumnIndexOrThrow("collect"))
                        ?: "null",
                    commentNum = getString(getColumnIndexOrThrow("commentNum"))
                        ?: "null",
                    timeDetails = getString(getColumnIndexOrThrow("timeDetails"))
                        ?: "null",
                )
                notes.add(note)
            }
            close()
        }
    } catch (e: Exception) {
        // 处理可能的异常，例如数据库无法打开的情况
        e.printStackTrace()
    }

    return notes;
}