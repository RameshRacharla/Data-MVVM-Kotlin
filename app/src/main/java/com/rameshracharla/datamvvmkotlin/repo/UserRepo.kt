package com.rameshracharla.datamvvmkotlin.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rameshracharla.datamvvmkotlin.api.ApiClient
import com.rameshracharla.datamvvmkotlin.api.ApiInterface
import com.rameshracharla.datamvvmkotlin.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepo {
    private val TAG = javaClass.simpleName
    fun getUserData(id: String): MutableLiveData<UserModel> {
        var mutableLiveData: MutableLiveData<UserModel> = MutableLiveData()
        var apiService: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)

        apiService.getUserDetails(id).enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        mutableLiveData.value = response.body()
                    }
                }
                Log.d(TAG, "onResponse: " + response.body().toString())

            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                Log.d(TAG, "onResponse: $call")
            }
        })
        return mutableLiveData
    }
}