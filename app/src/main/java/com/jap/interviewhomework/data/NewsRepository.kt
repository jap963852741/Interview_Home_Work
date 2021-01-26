package com.jap.interviewhomework.data

import com.jap.interviewhomework.data.model.NewsResponse
import io.reactivex.rxjava3.core.Observable

class NewsRepository(val dataSource: NewsDataSource) {

    fun news(): Observable<NewsResponse> {
        return dataSource.news()
    }

}