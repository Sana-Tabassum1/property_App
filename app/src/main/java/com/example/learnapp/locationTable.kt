package com.example.learnapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class locationTable(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val location: String,
    val Area: String,
    val propertyID: String
)