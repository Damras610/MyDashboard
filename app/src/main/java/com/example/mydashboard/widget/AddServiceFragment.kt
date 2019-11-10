package com.example.mydashboard.widget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.adapter.AddServiceListAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class AddServiceFragment : Fragment() {

    // View Model
    @Inject
    lateinit var viewModel: AddServiceFragment

    // Views
    private lateinit var serviceListView: RecyclerView

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

        serviceListView = view.findViewById(R.id.add_service_listview)
        serviceListView.adapter = AddServiceListAdapter();
    }


}
