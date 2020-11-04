package com.example.androidarchitecture.model

import android.util.Log
import com.example.androidarchitecture.model.database.UserDatabase
import com.example.androidarchitecture.model.entity.User
import com.example.androidarchitecture.util.MyApplication.Companion.context
import com.example.androidarchitecture.viewModel.androidTest.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class UserDBDataSource : UserRepository {

    private val userDao = UserDatabase.getUserDatabase(context).userDao()

    override fun findByName(name: String): Maybe<User> {

        var userFindByName: User? = null

        try {
            userFindByName = userDao.findByName(name)
        } catch (exception: Exception) {
            Maybe.error<Exception>(exception)
        }

        return if (userFindByName == null) {
            Maybe.empty()
        } else {
            Maybe.just(userFindByName)
        }
    }

    override fun save(user: User): Completable {

        if (userDao.findByName(user.name) != null) {
            return Completable.error(java.lang.RuntimeException("Username ${user.name} already exists"))
        }

        val password = md5(user.password)
        val userInsert = password?.let { User(user.name, it) }
        val id = userInsert?.let { userDao.addUser(it) }
        return if (id != null) {
            Completable.complete()
        } else {
            Completable.error(RuntimeException("save user error"))
        }
    }

    override fun getUsers(): List<User> {
        return userDao.getUsers()
    }

    private fun md5(input: String): String? {
        return try {
            val md = MessageDigest.getInstance("MD5")
            val hexString = StringBuilder()
            for (digestByte in md.digest(input.toByteArray())) hexString.append(
                String.format(
                    "%02X",
                    digestByte
                )
            )
            hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            null
        }
    }

    override fun clearUsers() {
        userDao.delete()
    }

}