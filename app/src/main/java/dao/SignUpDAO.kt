package dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.learnapp.signup


@Dao
interface signUpDAO {

    @Insert
     suspend fun insertData(signup: signup)

    @Update
    suspend fun updateData(signup: signup)

    @Delete
    suspend fun deleteData(signup: signup)

//    @Query("SELECT * FROM SIGNUP")
//    fun getsignupUser() :LiveData<List<signup>>

    @Query("SELECT * FROM signup")
    fun getUsers():List<signup>

    @Query("SELECT * FROM signup WHERE email = :email")
    suspend fun getUserByEmail(email:String): signup?

    @Query("SELECT name FROM signup WHERE email = :email")
    suspend fun getNameByEmail(email:String): String
}