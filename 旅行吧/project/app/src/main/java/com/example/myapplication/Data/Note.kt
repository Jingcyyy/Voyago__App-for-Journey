package com.example.myapplication.Data

class Note(
    var id: Int,
    var topic:String,//话题
    var title:String,//标题
    var cover:String,//封面图片连接
    var author:String,//作者主页连接，不一定会用上
    var profile:String,//作者头像
    var name:String,//作者名字
    var like:String,//喜欢
    var noteDetailsLink:String,//笔记详情连接
    var textDetails:String,//笔记内容
    var picture:String,//图片连接
    var time:String,
    var likes:String,
    var collect:String,
    var commentNum:String,
    var timeDetails:String,
)