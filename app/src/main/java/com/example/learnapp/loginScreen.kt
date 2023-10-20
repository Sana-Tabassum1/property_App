package com.example.learnapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.learnapp.databinding.ActivityLoginScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

class loginScreen : AppCompatActivity() {
    private lateinit var binding: ActivityLoginScreenBinding
    lateinit var sharePref: SharedPreferences
    lateinit var db:signUpDatabase
    var check:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        sharePref= getSharedPreferences("MyData", Context.MODE_PRIVATE)
        check = sharePref.getBoolean("flag",false)
        if(check){
            val intent = Intent(this,homeScreen::class.java)
            startActivity(intent)
        }
        else{

        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            sharePrefer()



            db = signUpDatabase.getDatabase(this)

            CoroutineScope(Dispatchers.IO).launch {

                val user= db.signupDao().getUserByEmail(email)

                if (user != null && user.password == password) {
                    // If login is successful, navigate to the user profile or dashboard activity
                    val intent = Intent(this@loginScreen,ProfileScreen::class.java)
                    startActivity(intent)
                    finish() // Optional: Finish the LoginActivity to prevent going back to it using the back button
                } else {
                    // Display an error message or handle invalid login
                    runOnUiThread {
                        Toast.makeText(this@loginScreen, "Invalid login", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }
}


    private fun sharePrefer() {
        binding.apply {
            sharePref.edit().putString("email", email.toString()).apply()
            sharePref.edit().putString("password",password.toString()).apply()
        }
    }
}