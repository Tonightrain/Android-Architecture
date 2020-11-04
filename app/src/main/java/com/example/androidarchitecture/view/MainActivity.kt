package com.example.androidarchitecture.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.androidarchitecture.R
import com.example.androidarchitecture.view.androidTest.LoginActivity
import com.example.androidarchitecture.view.architecture.ArchActivity

class MainActivity : AppCompatActivity() {

    private lateinit var archActivityButton: Button
    private lateinit var loginActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        archActivityButton = findViewById(R.id.button_arch_activity)
        archActivityButton.setOnClickListener {
            val intent = Intent().setClass(this,ArchActivity::class.java)
            startActivity(intent)
        }

        loginActivityButton = findViewById(R.id.button_login_activity)
        loginActivityButton.setOnClickListener {
            val intent = Intent().setClass(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}