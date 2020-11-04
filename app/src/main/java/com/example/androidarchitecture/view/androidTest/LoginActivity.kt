package com.example.androidarchitecture.view.androidTest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidarchitecture.R
import com.example.androidarchitecture.model.entity.User
import com.example.androidarchitecture.util.MyApplication
import com.example.androidarchitecture.viewModel.androidTest.LoginType
import com.example.androidarchitecture.viewModel.androidTest.LoginViewModel
import com.example.androidarchitecture.viewModel.androidTest.LoginViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var saveButton: Button
    private lateinit var usernameText: EditText
    private lateinit var passwordText: EditText
    private val loginSucceed: String = "Login successfully"
    private val notExit: String = "Username does not exist"
    private val errorPassword: String = "Password is invalid"
    private val insertUserSuccess: String = "Insert user success"
    private val userIsExit: String = "User is Exit"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.button_login)
        saveButton = findViewById(R.id.button_save)
        usernameText = findViewById(R.id.username)
        passwordText = findViewById(R.id.password)

        val loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory()).get(LoginViewModel::class.java)


        loginViewModel.loginResult.observe(this, { loginResult ->
            when (loginResult.loginStatus) {
                LoginType.NOT_EXIST -> Toast.makeText(this, notExit, Toast.LENGTH_LONG).show()
                LoginType.SUCCEED -> Toast.makeText(this, loginSucceed, Toast.LENGTH_LONG).show()
                LoginType.ERROR_PASSWORD -> Toast.makeText(this, errorPassword, Toast.LENGTH_LONG).show()
                LoginType.INSERT_SUCCESS -> Toast.makeText(this, insertUserSuccess, Toast.LENGTH_LONG).show()
                LoginType.EXIT -> Toast.makeText(this, userIsExit, Toast.LENGTH_LONG).show()
            }
        })


        loginButton.setOnClickListener {

            val user = User(usernameText.text.toString(), passwordText.text.toString())
            loginViewModel.login(user)
            Log.d("login", "${loginViewModel.loginResult.value?.loginStatus.toString()}")
        }

        saveButton.setOnClickListener {
            GlobalScope.launch {
                val user = User("android", "123456")
                loginViewModel.saveUser(user)

                Log.d("saveUser", "${user.id}-${user.name}-${user.password}")
                Log.d("saveUser", "${loginViewModel.loginResult.value?.loginStatus.toString()}")
            }
        }


        //测试功能
        val getUsersButton = findViewById<Button>(R.id.button_getUsers)
        getUsersButton.setOnClickListener {
            GlobalScope.launch {
                val list: List<User> = MyApplication.userDBDataSource.getUsers()
                Log.d("usersList", "${list[0].id}-${list[0].name}-${list[0].password}")
            }
        }

        val clearUser = findViewById<Button>(R.id.button_clearUser)
        clearUser.setOnClickListener {
            GlobalScope.launch {
                MyApplication.userDBDataSource.clearUsers()
                val list: List<User> = MyApplication.userDBDataSource.getUsers()
                Log.d("usersList", "${list.size}")
            }
        }
    }

}