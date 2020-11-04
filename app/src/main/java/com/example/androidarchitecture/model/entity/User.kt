package com.example.androidarchitecture.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User(var name: String, var password: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}