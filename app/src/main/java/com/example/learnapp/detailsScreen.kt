package com.example.learnapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnapp.databinding.ActivityDetailsScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class detailsScreen : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsScreenBinding
    lateinit var Database: propertyDatabase

    @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_LearnApp)
        binding = ActivityDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val searchView = menu?.findItem(R.id.searchView)?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Handle search when the user submits the query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Handle search as the user types
                filterData(newText)
                return true
            }
        })


        Database = propertyDatabase.getpropertyDatabase(this)
        binding.detailRecycler.layoutManager = LinearLayoutManager(this)
        CoroutineScope(Dispatchers.IO).launch {
            // Perform your database operations here
            val detailusers = Database.propertyDAO().getdetail()
            Log.i("data_test", "Items ${detailusers.size}")
            // Update the UI or perform other actions with the data

            withContext(Dispatchers.Main) {
                val userDetaileAdaptor = detailAdaptor(this@detailsScreen, detailusers)




                // Set the edit button click listener
                userDetaileAdaptor.setOnEditClickListener(object : detailAdaptor.EditButtonClickListener {
                    override fun onEditButtonClick(item: propertyEntitiy) {
                        // Handle the edit action here
                        // You can open an edit screen (e.g., EditActivity) and pass the item details
                        val intent = Intent(this@detailsScreen, EditpropertyScreen::class.java)
                      //  intent.putExtra("property_entity", item)
                        startActivity(intent)
                    }
                })

                // Set the delete button click listener
                userDetaileAdaptor.setOnDeleteClickListener(object : detailAdaptor.DeleteButtonClickListener {
                    override fun onDeleteButtonClick(item: propertyEntitiy) {
                        // Handle the delete action here
                        // Show a confirmation dialog
                        AlertDialog.Builder(this@detailsScreen)
                            .setTitle("Delete Item")
                            .setMessage("Are you sure you want to delete this item?")
                            .setPositiveButton("Yes") { dialog, which ->
                                // Delete the item from the database
                                // Example:
                                // Database.propertyDAO().deleteItem(item)
                                // Remove the item from the list and notify the adapter
                                // detailusers.remove(item)
                                // userDetaileAdapter.notifyDataSetChanged()
                            }
                            .setNegativeButton("No", null)
                            .show()
                    }
                })

                binding.detailRecycler.adapter = userDetaileAdaptor

                userDetaileAdaptor.notifyDataSetChanged()
            }

        }


    }

    private fun filterData(query: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            val filteredItems = Database.propertyDAO().searchItems("%$query%") // Use '%' for wildcard matching
            withContext(Dispatchers.Main) {
                // Update the RecyclerView adapter with the filtered data
                userDetaileAdaptor.updateData(filteredItems)
            }
        }
    }
}

