package com.example.mydashboard.widget.configuration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.description.ServiceDescription
import kotlinx.android.synthetic.main.list_item_add_service.view.*

class AddServiceListAdapter(
    var dataset: Array<ServiceDescription>,
    val onItemClickListener: View.OnClickListener
): RecyclerView.Adapter<AddServiceListAdapter.AddServiceViewHolder>() {

    inner class AddServiceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView
        val name: TextView
        val description: TextView

        init {
            image = view.list_item_add_service_image
            name = view.list_item_add_service_name
            description = view.list_item_add_service_description
            view.tag = this@AddServiceViewHolder
            view.setOnClickListener(onItemClickListener)
        }
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
    }


}