package com.example.mydashboard.widget.configuration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.mydashboard.R
import com.example.mydashboard.widget.configuration.adapter.AddWidgetListAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_widget.view.*
import javax.inject.Inject


class AddWidgetFragment : Fragment(), WidgetParamSettingDialogListener {

    // View Model
    @Inject
    lateinit var viewModel: AddWidgetViewModel

    // Views
    private lateinit var widgetListView: RecyclerView

    // List adapter
    private lateinit var adapter: AddWidgetListAdapter

    // Fragment args
    val args: AddWidgetFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_add_widget, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AddWidgetListAdapter(emptyArray(), View.OnClickListener {v ->
            val viewHolder = v.tag as AddWidgetListAdapter.AddWidgetViewHolder
            val widgetId = viewHolder.adapterPosition
            if (widgetId < adapter.dataset.size) {
                val dialog = WidgetParamSettingFragment(args.serviceId, widgetId, this)
                dialog.show(requireActivity().supportFragmentManager, WidgetParamSettingFragment::class.java.name)
            }
        })

        widgetListView = view.add_widget_listview
        widgetListView.adapter = adapter


        viewModel.loadWidgetsFromService(args.serviceId)
        viewModel.widgets.observe(this, Observer {widgets ->
            adapter.dataset = widgets
            adapter.notifyDataSetChanged()
        })
    }

    override fun onAddButton(d: WidgetParamSettingFragment) {
        viewModel.addWidget(requireContext(), d.serviceId, d.widgetId)
    }

    override fun onCancelButton(d: WidgetParamSettingFragment) {

    }
}
