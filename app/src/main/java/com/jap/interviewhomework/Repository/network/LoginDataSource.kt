package com.jap.interviewhomework.Repository.network

import com.jap.interviewhomework.Repository.network.api.ApiInterface
import com.jap.interviewhomework.Repository.bean.LoginResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    fun login(username: String, password: String):Observable<LoginResponse> {
        val baseUrl = "https://watch-master-staging.herokuapp.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val api = retrofit.create(ApiInterface::class.java)

        return api.login(username, password)
    }

}

