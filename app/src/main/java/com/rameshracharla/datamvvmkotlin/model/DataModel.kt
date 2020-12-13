package com.rameshracharla.datamvvmkotlin.model

public class DataModel {
    private var data: ArrayList<DataList>? = null

    fun getdata(): java.util.ArrayList<DataList> {
        return data!!
    }

    fun setdata(data: java.util.ArrayList<DataList>) {
        this.data = data
    }

}