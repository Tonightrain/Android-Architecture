package com.example.androidarchitecture.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidarchitecture.model.entity.User
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UserDao {

    @Query("select * from User where name=:name")
    fun findByName(name: String): User?

    @Insert
    fun addUser(user: User): Long

    @Query("select * from User")
    fun getUsers(): List<User>

    @Query("delete from User")
    fun delete()

}

