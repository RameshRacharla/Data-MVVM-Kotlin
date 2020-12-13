package com.rameshracharla.datamvvmkotlin.utils

import android.app.Application
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.provider.Settings
import androidx.appcompat.app.AlertDialog

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val isNetworkAvailable: Boolean
        get() {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            var activeNetworkInfo: NetworkInfo? = null
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.activeNetworkInfo
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }

    /* No Internet Connection Dialog */
    fun noInternetConnectionDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("No Internet Connection!")
        builder.setMessage("Check your connection or try again.")
        builder.setPositiveButton(
            "Settings"
        ) { dialog, id ->
            dialog.dismiss()
            val intent = Intent(Settings.ACTION_SETTINGS)
            startActivity(intent)
        }
        val dialog = builder.create()
        dialog.setCancelable(true)
        dialog.show()
    }

    companion object {
        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}
