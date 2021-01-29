package com.jap.interviewhomework.Repository.network

import com.jap.interviewhomework.Repository.network.api.ApiInterface
import com.jap.interviewhomework.Repository.bean.UpdateResponse
import com.jap.interviewhomework.ui.update.Timezone
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UpdateDataSource {
    fun update(sessionToken: String , objectId: String , timezone: Timezone ): Observable<UpdateResponse> {
        val baseUrl = "https://watch-master-staging.herokuapp.com/api/users/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        return api.update(sessionToken,objectId,timezone)
    }
}