package com.rameshracharla.datamvvmkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rameshracharla.datamvvmkotlin.model.DataModel
import com.rameshracharla.datamvvmkotlin.repo.DataRepo

class MainViewModel() : ViewModel() {

    var datarepo: DataRepo? = null;
    var mutableLiveData: MutableLiveData<DataModel>? = null

    init {
        datarepo = DataRepo()
    }

    fun getData(): LiveData<DataModel> {
        if (mutableLiveData == null) {
            mutableLiveData = datarepo?.requestData()
        }
        return mutableLiveData!!
    }
}