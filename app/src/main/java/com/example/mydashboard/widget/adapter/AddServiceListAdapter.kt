package com.example.mydashboard.widget.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.widget.Services
import javax.inject.Inject

class AddServiceListAdapter @Inject constructor(
    private val services: Services
) : RecyclerView.Adapter<AddServiceListAdapter.AddServiceViewHolder>() {


    class AddServiceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddServiceViewHolder {

    }

    override fun getItemCount(): Int {
        return services.all.size
    }

    override fun onBindViewHolder(holder: AddServiceViewHolder, position: Int) {

    }
}