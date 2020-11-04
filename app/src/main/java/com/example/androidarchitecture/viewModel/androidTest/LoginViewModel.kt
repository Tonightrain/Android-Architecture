package com.example.androidarchitecture.viewModel.androidTest

import android.widget.GridLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.model.database.UserDatabase
import com.example.androidarchitecture.model.entity.User
import com.example.androidarchitecture.util.MyApplication
import com.example.androidarchitecture.view.androidTest.LoginActivity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class LoginViewModel : ViewModel() {

    val loginResult: LiveData<LoginResult>
        get() = _loginResult

    private val _loginResult = MutableLiveData<LoginResult>()

    private val userDBDataSource = MyApplication.userDBDataSource

    fun login(user: User) {

        val userFindByName: Maybe<User> = userDBDataSource.findByName(user.name)

        userFindByName.subscribeBy(
            onComplete = {
                _loginResult.value = LoginResult(LoginType.NOT_EXIST)
            },
            onError = {

            },
            onSuccess = {
                if (user.password == it.password) {
                    _loginResult.value = LoginResult(LoginType.SUCCEED)
                } else {
                    _loginResult.value = LoginResult(LoginType.ERROR_PASSWORD)
                }
            }
        )
    }

    fun saveUser(user: User) {

        val saveUser: Completable = userDBDataSource.save(user)

        GlobalScope.launch (Dispatchers.Main){
            saveUser.subscribeBy(
                onComplete = {
                    _loginResult.postValue(LoginResult(LoginType.INSERT_SUCCESS))
                },
                onError = {
                    _loginResult.postValue(LoginResult(LoginType.EXIT))
                }
            )
        }


    }
}