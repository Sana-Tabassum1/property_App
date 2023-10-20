package com.example.learnapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dao.signUpDAO


@Database(entities = [signup::class], version = 1)

abstract class signUpDatabase : RoomDatabase() {
    abstract fun signupDao(): signUpDAO

    companion object{
        @Volatile
        private var INSTANCE: signUpDatabase?=null

        fun getDatabase(context: Context): signUpDatabase{
            if (INSTANCE==null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        signUpDatabase::class.java,
                        "signDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }



    }

}