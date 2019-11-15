package com.example.mydashboard.widget.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.configuration.adapter.AddServiceListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_service.view.*
import javax.inject.Inject


class AddServiceFragment : Fragment() {

    // View Model
    @Inject
    lateinit var viewModel: AddServiceViewModel

    // Views
    private lateinit var serviceListView: RecyclerView

    // List adapter
    private lateinit var adapter: AddServiceListAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        adapter = AddServiceListAdapter(emptyArray(), View.OnClickListener {v ->
            val viewHolder = v.tag as AddServiceListAdapter.AddServiceViewHolder
            val serviceId = viewHolder.adapterPosition
            if (serviceId < adapter.dataset.size) {
                val action =
                    AddServiceFragmentDirections.actionAddServiceFragmentToAddWidgetFragment(
                        serviceId
                    )
                navController.navigate(action)
            }
        })

        // Init the views
        serviceListView = view.add_service_listview
        serviceListView.adapter = adapter

        // Observe models
        viewModel.services.observe(this, Observer {services ->
            adapter.dataset = services
            adapter.notifyDataSetChanged()
        })
    }

}
