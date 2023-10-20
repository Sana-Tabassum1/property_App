package com.example.learnapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.room.Database
import com.example.learnapp.databinding.ActivityProfileScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileScreen : AppCompatActivity() {
    private lateinit var binding: ActivityProfileScreenBinding
//    private lateinit var name:String
   lateinit var sharePref: SharedPreferences
    lateinit var Database: signUpDatabase
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnApp)
        binding= ActivityProfileScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharePref= getSharedPreferences("MyData", Context.MODE_PRIVATE)
        val currentuserEmail= sharePref.getString("email","")
        Database = signUpDatabase.getDatabase(this)
            CoroutineScope(Dispatchers.IO).launch {
               val name = Database.signupDao().getUserByEmail(currentuserEmail.toString())
                /*Database.signupDao()
                    .insertData(
                        signup(*/
                withContext(Dispatchers.Main) {
                    binding.nameprofile.text = name!!.name
                    binding.emailProfile.text=name.email
                    binding.phoneProfile.text=name.phoneNo
                    binding.passwordProfile.text=name.password
                    /*if(users != null) {
                        binding.nameprofile.text = users.name
                        binding.emailProfile.text=users.email
                        binding.phoneProfile.text=users.phoneNo
                        binding.passwordProfile.text=users.password
                    }*/
                }
                            /*0,
                            binding.nameprofile.text.toString(),
                            binding.emailProfile.text.toString(),
                            binding.phoneProfile.text.toString(),
                            binding.passwordProfile.text.toString()*/
                        /*)
                    )*/
            }


        binding.calculate.setOnClickListener {
                        val wt = binding.weightText.text.toString().toInt()
                        val ft = binding.heightText.text.toString().toInt()
                        val inch = binding.incheText.text.toString().toInt()
                        val totalInch = ft * 12 + inch
                        val totalCm = totalInch * 2.54
                        val totalM = totalCm / 100
                        val BMI = wt / (totalM * totalM)
                        if (BMI > 25) {
                            binding.Result.text = "You are overweight"

                        } else if (BMI < 18) {
                            binding.Result.text = "You are underweight"
                        } else {
                            binding.Result.text = "You are healthy"

                        }
        }
    }

}