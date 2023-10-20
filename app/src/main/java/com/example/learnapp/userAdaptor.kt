package com.example.learnapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnapp.databinding.UserdataBinding

class userAdaptor(private val context: Context, private val userlist: List<signup>):RecyclerView.Adapter<userAdaptor.MyHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(UserdataBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.name.text= userlist[position].name
        holder.number.text=userlist[position].phoneNo
        holder.root.setOnClickListener {

        }


    }

    class MyHolder(binding: UserdataBinding) :RecyclerView.ViewHolder(binding.root){
              val name = binding.NameUD
              val number = binding.NumberUD
        val root = binding.root
    }
}