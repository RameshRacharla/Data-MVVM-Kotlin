package com.rameshracharla.datamvvmkotlin.api

import com.rameshracharla.datamvvmkotlin.model.DataModel
import com.rameshracharla.datamvvmkotlin.model.UserModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("users")
    fun getUsers(): Call<DataModel>

    @GET("users")
    fun getUserDetails(
        @Query("id") id: String
    ): Call<UserModel>

}