package com.example.learnapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "signup")
data class signup(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name:String,
    val email:String,
    val phoneNo: String,
    val password: String
)


