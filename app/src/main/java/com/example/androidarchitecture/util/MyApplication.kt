package com.example.androidarchitecture.util

import android.app.Application
import android.content.Context
import com.example.androidarchitecture.model.UserDBDataSource
import com.example.androidarchitecture.viewModel.androidTest.UserRepository

class MyApplication : Application() {

    companion object {
        lateinit var context: Context
        lateinit var userDBDataSource: UserRepository

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        userDBDataSource = UserDBDataSource()

    }
}