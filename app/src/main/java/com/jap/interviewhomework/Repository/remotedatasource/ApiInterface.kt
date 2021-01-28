package com.jap.interviewhomework.Repository.remotedatasource

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*

interface ApiInterface {

    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @GET("login")
    fun login(@Query("username") username : String,
              @Query("password") password : String): Observable<LoginResponse>

    @GET("news.json")
    fun news(): Observable<NewsResponse>

    @Headers("X-Parse-Application-Id:vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD")
    @PUT("{objectId}")
    fun update(@Header("X-Parse-Session-Token") sessionToken: String,
               @Path("objectId") objectId: String): Observable<UpdateResponse>
}