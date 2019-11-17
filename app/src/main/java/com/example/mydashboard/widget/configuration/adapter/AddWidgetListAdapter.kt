package com.example.mydashboard.widget.configuration.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.description.WidgetDescription
import kotlinx.android.synthetic.main.list_item_add_widget.view.*

class AddWidgetListAdapter(
    var dataset: Array<WidgetDescription>,
    val onItemClickListener: View.OnClickListener
): RecyclerView.Adapter<AddWidgetListAdapter.AddWidgetViewHolder>() {

    inner class AddWidgetViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val description: TextView

        init {
            name = view.list_item_add_widget_name
            description = view.list_item_add_widget_description
            view.tag = this@AddWidgetViewHolder
            view.setOnClickListener(onItemClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddWidgetViewHolder {
        val itemView = LayoutInflater.from(parent.context).
            inflate(R.layout.list_item_add_widget, parent, false)
        return AddWidgetViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: AddWidgetViewHolder, position: Int) {
        holder.name.setText(dataset[position].nameResId)
        holder.description.setText(dataset[position].descriptionResId)
    }

}