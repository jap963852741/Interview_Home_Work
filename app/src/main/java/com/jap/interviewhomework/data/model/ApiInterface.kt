package com.jap.interviewhomework.data.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiInterface {

    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @GET("login")
    fun login(@Query("username") username : String,
              @Query("password") password : String): Call<LoginResponse>
//    fun login (@Body loginRequest: LoginRequest): Call<LoginResponse>

}