package com.rameshracharla.datamvvmkotlin.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rameshracharla.datamvvmkotlin.api.ApiClient
import com.rameshracharla.datamvvmkotlin.api.ApiInterface
import com.rameshracharla.datamvvmkotlin.model.DataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataRepo {
    private val TAG = javaClass.simpleName
    fun requestData(): MutableLiveData<DataModel> {
        var mutableLiveData: MutableLiveData<DataModel> = MutableLiveData()
        var apiService: ApiInterface = ApiClient.getClient()!!.create(ApiInterface::class.java)

        apiService.getUsers().enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        mutableLiveData.value = response.body()
                    }
                }
                Log.d(TAG, "onResponse: " + response.body().toString())

            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.d(TAG, "onResponse: $call")
            }
        })
        return mutableLiveData
    }
}