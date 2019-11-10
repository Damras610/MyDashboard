package com.example.mydashboard.widget

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.adapter.AddServiceListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_service.view.*
import javax.inject.Inject


class AddServiceFragment : Fragment() {

    // View Model
    @Inject
    lateinit var viewModel: AddServiceViewModel

    // Views
    private lateinit var serviceListView: RecyclerView

    // List adapter
    private val adapter: AddServiceListAdapter
    init {
        adapter = AddServiceListAdapter(emptyArray())
    }

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

        serviceListView = view.add_service_listview
        serviceListView.adapter = adapter;

        viewModel.services.observe(this, Observer {services ->
            adapter.dataset = services
            adapter.notifyDataSetChanged()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        requireActivity().appbarlayout
    }

}
