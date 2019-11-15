package com.example.mydashboard.widget.configuration

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.mydashboard.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class WidgetParamSettingFragment(
    val serviceId: Int,
    val widgetId: Int
) : DialogFragment() {

    // View Model
    @Inject
    lateinit var viewModel: WidgetParamSettingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        viewModel.loadParamController(serviceId, widgetId)

        val activity = requireActivity()
        val inflater = activity.layoutInflater

        val builder = AlertDialog.Builder(activity)
        builder.setView(inflater.inflate(R.layout.fragment_widget_parameter_setting, null))
            .setPositiveButton(R.string.add, DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.dismiss()
            })
        return builder.create()
    }

}
