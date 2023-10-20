package com.example.learnapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [propertyEntitiy::class, locationTable::class], version = 2)
abstract class propertyDatabase : RoomDatabase() {
    abstract fun propertyDAO(): propertyDAO

    companion object {
        @Volatile
        private var INSTANCE: propertyDatabase? = null

        fun getpropertyDatabase(context: Context): propertyDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        propertyDatabase::class.java,
                        "propertyDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }


    }
}