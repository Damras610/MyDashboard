package com.example.mydashboard.widget.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.ServiceDescription
import kotlinx.android.synthetic.main.list_item_add_service.view.*

class AddServiceListAdapter(
    var dataset: Array<ServiceDescription>
): RecyclerView.Adapter<AddServiceListAdapter.AddServiceViewHolder>() {

    class AddServiceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.list_item_add_service_image
        val name: TextView = view.list_item_add_service_name
        val description: TextView = view.list_item_add_service_description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).
                inflate(R.layout.list_item_add_service, parent, false)
        return AddServiceViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AddServiceViewHolder, position: Int) {
        holder.image.setImageResource(dataset[position].imageResId)
        holder.name.setText(dataset[position].nameResId)
        holder.description.setText(dataset[position].descriptionResId)

        // TODO Add Click Listener to widgets
    }
}