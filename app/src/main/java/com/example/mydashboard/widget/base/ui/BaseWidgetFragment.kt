package com.example.mydashboard.widget.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mydashboard.R
import kotlinx.android.synthetic.main.fragment_widget_base.view.*

open class BaseWidgetFragment(
    private val placeholder: Boolean = true,
    private val serviceName: String = "",
    private val widgetName: String = ""
) : Fragment() {

    companion object {
        val ARGUMENT_NAME = "widget_param"
    }

    // Views
    private lateinit var serviceNameTextView: TextView
    private lateinit var widgetNameTextView: TextView
    private lateinit var widgetParamsTextView: TextView

    // View Model
    private lateinit var widgetParams: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (placeholder == true)
            return

        widgetParams = arguments?.get(ARGUMENT_NAME) as Map<String, String>
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ret = super.onCreateView(inflater, container, savedInstanceState)

        if (placeholder == true)
            return ret

        return inflater.inflate(R.layout.fragment_widget_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (placeholder == true)
            return

        serviceNameTextView = view.widget_base_service_name
        widgetNameTextView = view.widget_base_widget_name
        widgetParamsTextView = view.widget_base_widget_params

        updateView()
    }

    private fun updateView() {
        serviceNameTextView.text = serviceName
        widgetNameTextView.text = widgetName

        var str = ""
        widgetParams.forEach {
            str = str.plus("\"${it.value}\" as \"${it.key}\". ")
        }
        widgetParamsTextView.text = str
    }

}