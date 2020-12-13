package com.rameshracharla.datamvvmkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rameshracharla.datamvvmkotlin.model.DataModel
import com.rameshracharla.datamvvmkotlin.model.UserModel
import com.rameshracharla.datamvvmkotlin.repo.UserRepo

class UserDataViewModel : ViewModel() {
    var userRepo: UserRepo? = null;
    var mutableLiveData: MutableLiveData<UserModel>? = null

    init {
        userRepo = UserRepo()
    }

    fun getUserData(id: String): LiveData<UserModel> {
        if (mutableLiveData == null) {
            mutableLiveData = userRepo?.getUserData(id)
        }
        return mutableLiveData!!
    }

}