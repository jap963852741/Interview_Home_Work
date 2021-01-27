package com.jap.interviewhomework.data.model

data class NewsResponse (
    val updateTime: String,
    val News: List<News>
)
data class News (
    val chtmessage: String,
    val engmessage: String,
    val starttime: String,
    val endtime: String,
    val updatetime: String,
    val content: String,
    val url: String? = null
)