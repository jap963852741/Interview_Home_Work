package com.jap.interviewhomework.Repository

import com.jap.interviewhomework.Repository.remotedatasource.ApiInterface
import com.jap.interviewhomework.Repository.remotedatasource.NewsResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsDataSource {
    fun news(): Observable<NewsResponse> {
        val baseUrl = "https://tcgbusfs.blob.core.windows.net/dotapp/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        return api.news()
    }
}