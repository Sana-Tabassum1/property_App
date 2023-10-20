package com.example.learnapp

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "propertyTable")
data class propertyEntitiy(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val room: String,
    val bedrooms: String,
    val bathrooms: String,
    val floor: String,
    val Area: String,
    val location: String,
    val type: String,
    val interior: String,
    val size: String
    //val email:String
)



