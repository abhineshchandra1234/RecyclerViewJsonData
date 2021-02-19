package com.eegrab.recyclerretrofitpractice

import okhttp3.Response
import retrofit2.http.GET

interface UserApi {

    @GET("/posts")
    suspend fun getUserDetails(): retrofit2.Response<List<UserData>>
}