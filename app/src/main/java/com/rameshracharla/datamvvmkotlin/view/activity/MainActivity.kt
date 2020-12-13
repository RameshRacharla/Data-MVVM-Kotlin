package com.rameshracharla.datamvvmkotlin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.rameshracharla.datamvvmkotlin.databinding.ActivityMainBinding
import com.rameshracharla.datamvvmkotlin.model.DataModel
import com.rameshracharla.datamvvmkotlin.utils.MyApplication
import com.rameshracharla.datamvvmkotlin.view.adapter.MainActivityAdapter
import com.rameshracharla.datamvvmkotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var mainadapter: MainActivityAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.recyclerview.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.setLayoutManager(layoutManager)
        binding.recyclerview.setItemAnimator(DefaultItemAnimator())
        mainadapter = MainActivityAdapter(this)
        binding.recyclerview.adapter = mainadapter

        getData()

    }

    private fun getData() {
        if (MyApplication.instance!!.isNetworkAvailable) {
            binding.progressBar.visibility = View.VISIBLE
            val viewModel = MainViewModel()

            viewModel.getData().observe(this, object : Observer<DataModel> {
                override fun onChanged(t: DataModel?) {
                    binding.progressBar.visibility = View.GONE
                    if (t != null) {
                        mainadapter?.addDataList(t.getdata())
                    }
                    mainadapter?.notifyDataSetChanged()
                }

            })
        } else {
            MyApplication.instance!!.noInternetConnectionDialog()
        }
    }
}