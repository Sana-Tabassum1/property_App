package com.example.learnapp

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface locationDAO {

    @Insert
    suspend fun insertData(location:locationTable)

}