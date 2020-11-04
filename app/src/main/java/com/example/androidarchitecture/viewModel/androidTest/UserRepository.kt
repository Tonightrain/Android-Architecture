package com.example.androidarchitecture.viewModel.androidTest

import com.example.androidarchitecture.model.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

interface UserRepository {

    fun findByName(name:String):Maybe<User>

    fun save(user: User) : Completable

    fun getUsers() : List<User>

    fun clearUsers()
}