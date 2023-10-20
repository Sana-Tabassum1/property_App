package com.example.learnapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.learnapp.databinding.ActivityHomeScreenBinding

class homeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.User.setOnClickListener {
            val intent = Intent(this, UserScreen::class.java)
            startActivity(intent)
        }

        binding.Profile.setOnClickListener {
            val intent = Intent(this, ProfileScreen::class.java)
            startActivity(intent)
        }

        binding.property.setOnClickListener {
            val intent = Intent(this, propertyScreen::class.java)
            startActivity(intent)
        }
        binding.detaile.setOnClickListener {
            val intent = Intent(this, detailsScreen::class.java)
            startActivity(intent)
        }
    }
}