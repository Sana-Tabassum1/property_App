package com.example.learnapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [locationTable::class], version = 3)
abstract class locationDatabase : RoomDatabase() {
    abstract fun locationDao(): locationDAO

    companion object {
        @Volatile
        private var INSTANCE: locationDatabase? = null

        fun getlocationDatabase(context: Context): locationDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        locationDatabase::class.java,
                        "locationDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }


    }
}