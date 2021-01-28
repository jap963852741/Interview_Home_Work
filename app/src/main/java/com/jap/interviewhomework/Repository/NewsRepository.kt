package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.remotedatasource.NewsResponse
import io.reactivex.rxjava3.core.Observable

class NewsRepository(val dataSource: NewsDataSource) {

    fun news(): Observable<NewsResponse> {
        return dataSource.news()
    }

}