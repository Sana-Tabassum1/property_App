package com.example.learnapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.learnapp.databinding.InfoscreenBinding

class detailAdaptor(
    private val context: Context,
    private val detaillist: List<propertyEntitiy>,
//    onEditClickListener: (Any) -> Unit,
//    param: (Any) -> Unit,
//    private val onEditClickListener: (Any) -> Unit,
//    private val onDeleteClickListener: (propertyEntitiy) -> Unit
) :
    RecyclerView.Adapter<detailAdaptor.MyHolder>() {


    private var editBtnListener: detailAdaptor.EditButtonClickListener? = null
    private var deleteBtnListener: detailAdaptor.DeleteButtonClickListener? = null

    interface EditButtonClickListener {
        fun onEditButtonClick(item: propertyEntitiy)
    }
    interface DeleteButtonClickListener {
        fun onDeleteButtonClick(item: propertyEntitiy)
    }

    fun setOnEditClickListener(listener: detailAdaptor.EditButtonClickListener) {
        editBtnListener= listener
    }
    fun setOnDeleteClickListener(listener: detailAdaptor.DeleteButtonClickListener) {
        deleteBtnListener= listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(InfoscreenBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount(): Int {
        return detaillist.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.Room.text = detaillist[position].room
        holder.bedRoom.text = detaillist[position].bedrooms
        holder.bathroom.text = detaillist[position].bathrooms
        holder.Floor.text = detaillist[position].floor
        holder.area.text = detaillist[position].Area
        holder.Location.text = detaillist[position].location
        holder.SR.text = detaillist[position].type
        holder.FurNonFur.text = detaillist[position].interior
        holder.sizeHouse.text = detaillist[position].size

        holder.EditInfo.setOnClickListener {
//            editBtnListener?.onEditButtonClick(detaillist[position])
//            val propertyEntity = detaillist[position]
//            //onEditClickListener(propertyEntity) // Call the edit click listener callback
            val propertyEntity = detaillist[position]
            val intent = Intent(context, EditpropertyScreen::class.java)
           // intent.putExtra("property_entity", propertyEntity)
            context.startActivity(intent)

        }

        holder.DeleteInfo.setOnClickListener {

            deleteBtnListener?.onDeleteButtonClick(detaillist[position])
          //  detaillist.removeAt(position)
            notifyItemRemoved(position)
            val propertyEntity = detaillist[position]
            showDeleteConfirmationDialog(propertyEntity)
            //onDeleteClickListener(propertyEntity) // Call the delete click listener callback
        }

    }


    class MyHolder(binding: InfoscreenBinding) : RecyclerView.ViewHolder(binding.root) {
        val Room = binding.NumRoom
        val bedRoom = binding.NumbedRoom
        val bathroom = binding.NumbathRoom
        val Floor = binding.floor
        val area = binding.Area
        val Location = binding.houselocation
        val SR = binding.rentSale
        val FurNonFur = binding.furNonFur
        val sizeHouse = binding.sppinerOpt
        val EditInfo = binding.edit
        val DeleteInfo = binding.delete

        val root = binding.root
    }

    private fun showDeleteConfirmationDialog(propertyEntity: propertyEntitiy) {
        AlertDialog.Builder(context)
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Yes") { _, _ ->
                // Handle the deletion here
                // You can delete the item from the database and notify the adapter
                // Database.propertyDAO().deleteItem(propertyEntity)
                // detaillist.remove(propertyEntity)
                // notifyDataSetChanged()
            }
            .setNegativeButton("No", null)
            .show()
    }

    fun updateData(newData: List<propertyEntitiy>) {
        //detaillist = newData
        notifyDataSetChanged()
    }


}