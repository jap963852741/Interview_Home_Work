package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.bean.NewsResponse
import com.jap.interviewhomework.Repository.network.NewsDataSource
import io.reactivex.rxjava3.core.Observable

class NewsRepository(val dataSource: NewsDataSource) {

    fun news(): Observable<NewsResponse> {
        return dataSource.news()
    }

}