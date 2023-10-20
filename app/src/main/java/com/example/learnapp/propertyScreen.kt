package com.example.learnapp

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.learnapp.databinding.ActivityPropertyScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class propertyScreen : AppCompatActivity() {
    private lateinit var binding: ActivityPropertyScreenBinding
    lateinit var Database: propertyDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(com.example.learnapp.R.style.Theme_LearnApp)
        binding = ActivityPropertyScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val options = arrayOf("Marla 3", "Marla 5", "Marla 7","Marla 10")

        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = options[position]
                // Do something with the selected option
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case where nothing is selected (optional)
            }
        }
        val sharePref = getSharedPreferences("MyData", MODE_PRIVATE)
        val currentuserEmail = sharePref.getString("email", "").toString()

        val radioGroup = binding.selectRs // Assuming this is your RadioGroup
        val FNOnFradioGroup = binding.furnitureGroup
// Get the ID of the selected RadioButton within the RadioGroup
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId
        val selectedbuttonId = radioGroup.checkedRadioButtonId

        binding.saveData.setOnClickListener {


            Database = propertyDatabase.getpropertyDatabase(this)

            CoroutineScope(Dispatchers.IO).launch {
                val room = binding.room.text.toString()
                val BR = binding.bedroom.text.toString()
                val BadR = binding.bathroom.text.toString()
                val floor = binding.floor.text.toString()
                val area = binding.area.text.toString()
                val location = binding.location.text.toString()
                val selectedType = when (selectedRadioButtonId) {
                    binding.rent.id -> "Rent"
                    binding.sale.id -> "Sale"
                    else -> "Default" // Handle the default case if no RadioButton is selected
                }
                val selectedType2 = when (selectedbuttonId) {
                    binding.furnitured.id -> "furnitured"
                    binding.nonfurnitured.id -> "nonfurnitured"
                    else -> "Default" // Handle the default case if no RadioButton is selected
                }

                val spinner = binding.spinner.selectedItem.toString()
                val user =
                    propertyEntitiy(0, room, BR, BadR, floor, area, location, selectedType, selectedType2, spinner)
                Database.propertyDAO().insertData(user)
            }

            Toast.makeText(this, "Data Save Successfully", Toast.LENGTH_SHORT).show()
        }

    }
}