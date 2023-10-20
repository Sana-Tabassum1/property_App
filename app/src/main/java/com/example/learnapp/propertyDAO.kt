package com.example.learnapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.learnapp.models.PropertyLocationData

@Dao
interface propertyDAO {

    @Insert
    suspend fun insertData(property: propertyEntitiy)

    @Query("SELECT * FROM propertyTable")
    fun getdetail(): List<propertyEntitiy>


    @Query("SELECT * FROM propertyTable INNER JOIN location ON propertyTable.id = location.propertyID")
    suspend fun getPropertyLocationData(): List<PropertyLocationData>

    @Delete
    fun deleteProperty(PropertyEntity: propertyEntitiy)


    @Query("SELECT * FROM property_entity WHERE room LIKE :query OR bedrooms LIKE :query OR bathrooms LIKE :query OR floor LIKE :query OR Area LIKE :query OR location LIKE :query")
    suspend fun searchItems(query: String): List<propertyEntitiy>


}