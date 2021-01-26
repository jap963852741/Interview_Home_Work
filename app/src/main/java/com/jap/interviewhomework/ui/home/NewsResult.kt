package com.jap.interviewhomework.ui.home

import com.jap.interviewhomework.data.model.News

/**
 * Authentication result : success (user details) or error message.
 */
data class NewsResult(
        val success: ArrayList<News>? = null,
        val error: Int? = null
)