package com.example.learnapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.learnapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.jar.Attributes.Name

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var Database: signUpDatabase
   lateinit var sharePref: SharedPreferences
    //var check:Boolean = false

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnApp)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener{
            startActivity(Intent(this@MainActivity, loginScreen::class.java))
            finish()
        }
        val sharePref= getSharedPreferences("MyData", Context.MODE_PRIVATE)


        Database= Room.databaseBuilder(applicationContext,signUpDatabase::class.java,
            "signDB").build()



        binding.signUp.setOnClickListener {
//            sharePrefer()
            validation()

            CoroutineScope(Dispatchers.IO).launch {
                Database.signupDao()
                    .insertData(
                        signup(
                            0,
                            binding.Name.text.toString(),
                             binding.email.text.toString(),
                            binding.phoneNo.text.toString(),
                            binding.password.text.toString()
                        )
                    )
            }
            Toast.makeText(this, "Data save successfully", Toast.LENGTH_SHORT).show()
            Log.d("DataSaved", "DAta")

            val mail = binding.email.text.toString()
            Toast.makeText(this, mail, Toast.LENGTH_SHORT).show()

            val editor = sharePref.edit()
            editor.putString("email", mail).apply()

            val intent = Intent(this, homeScreen::class.java)
            startActivity(intent)

        }
    }

    private fun validation() {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
        val nameregex = "[a-zA-Z\\s]+\$"
        val passwordregex = "^(?=.*[0-9])(?=.*[!@#\\\$%^&*])[a-zA-Z0-9!@#\\\$%^&*]{8,}\\\$"


        binding.apply {
            if (Name!!.length() == 0) {
                Toast.makeText(applicationContext, "please enter the Name", Toast.LENGTH_SHORT)
                    .show()
            } else if (email.text.toString().matches(emailRegex.toRegex())) {
                Toast.makeText(
                    this@MainActivity, "Valid email address",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (phoneNo.length() != 11) {
                phoneNo.error = "Phone Number must be 11 characters"
            } else if (password.text.toString().matches(passwordregex.toRegex())) {
                Toast.makeText(
                    this@MainActivity, "Valid email address",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

//    object validation {
//        fun nameValidation(context: Context, name: String, edName: EditText): Boolean {
//            if (!name.matches("^[a-zA-Z.\\s]+\$".toRegex())) {
//                edName.error = "Name Must Contain Only Alphabets"
//                return false
//            }
//            return true
//        }
//
//        fun phoneValidation(context: Context, phone: String, edPhone: EditText): Boolean {
//            if (phone.length != 11) {
//                edPhone.error = "Phone Number must be 11 characters"
//                return false
//            }
//            return true
//        }
//
//        fun emailValidation(context: Context, email: String, edEmail: EditText): Boolean {
//            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                edEmail.error = "Email format is not correct"
//                return false
//            }
//            return true
//        }
//
//        fun passwordValidation(context: Context, password: String, edPassword: EditText): Boolean {
//
//            val passwordRegex = "^(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,}\$"
//            val regexResult = password.matches(passwordRegex.toRegex())
//
//            if (!regexResult || password.length != 8) {
//                edPassword.error = "Enter 8 characters with alphabet and special characters"
//                return false
//            }
//            return true
//        }
//
//
//    }

    /*private fun sharePrefer() {
        binding.apply {
            sharePref.edit().putString("email", email.toString()).apply()
        }

    }*/

}
