package com.rameshracharla.datamvvmkotlin.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.rameshracharla.datamvvmkotlin.R
import com.rameshracharla.datamvvmkotlin.databinding.ActivityUserdataBinding
import com.rameshracharla.datamvvmkotlin.utils.MyApplication
import com.rameshracharla.datamvvmkotlin.viewmodel.UserDataViewModel
import com.squareup.picasso.Picasso

class UserDataActivity : AppCompatActivity() {
    var id: String? = null
    var binding: ActivityUserdataBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userdata)
        id = intent.getStringExtra("userData")
        getUserDetails()
    }

    private fun getUserDetails() {
        if (MyApplication.instance!!.isNetworkAvailable) {
            binding!!.progressBar.visibility = View.VISIBLE
            val viewModel = UserDataViewModel()

            viewModel.getUserData(id!!).observe(this,
                Observer { userModel ->
                    binding!!.progressBar.visibility = View.GONE
                    binding!!.userdata = userModel.data
                    Picasso.get()
                        .load(userModel.data!!.avatar)
                        .placeholder(R.drawable.ic_image)
                        .into(binding!!.image)
                })
        } else {
            MyApplication.instance!!.noInternetConnectionDialog()
        }
    }
}