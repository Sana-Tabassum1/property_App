package com.example.learnapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.learnapp.databinding.ActivityUserScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserScreen : AppCompatActivity() {
    private lateinit var binding: ActivityUserScreenBinding
    //private lateinit var userAdaptor: userAdaptor
    lateinit var Database: signUpDatabase

    private val scope = CoroutineScope(Dispatchers.IO)
    private lateinit var userList: List<signup>
    @SuppressLint("NotifyDataSetChanged", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnApp)
        binding = ActivityUserScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Database = Room.databaseBuilder(
            this, signUpDatabase::class.java,
            "signDB"
        ).build()
        binding.userRecycler.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {            // Perform your database operations here
        var users = Database.signupDao().getUsers()
        // Log.d("data_test","${users.first().name}")
        // Update the UI or perform other actions with the data
        withContext(Dispatchers.Main) {
            val userAdaptor = userAdaptor(this@UserScreen, users)
            binding.userRecycler.adapter = userAdaptor

            userAdaptor.notifyDataSetChanged()
        }
    }
        }

    }
