package com.bignerdranch.android.androidacademy

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.androidacademy.viewmodel.TestingDataClass
import com.squareup.picasso.Picasso

class CustomAdapter(private val mList: List<Movies>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return MyViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val itemsViewModel = mList?.get(position)
        holder.textView.text = itemsViewModel.name
        Log.d("testLogs", "onBindViewHolder: ${holder.textView.text} ")
        Picasso.get().load(itemsViewModel.poster.url).into(holder.imageView)


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class MyViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
}