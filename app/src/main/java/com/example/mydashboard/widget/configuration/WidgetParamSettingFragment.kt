package com.example.mydashboard.widget.configuration

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mydashboard.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.field_widget_parameter_setting.view.*
import javax.inject.Inject


class WidgetParamSettingFragment(
    val serviceId: Int,
    val widgetId: Int,
    val dialogListener: WidgetParamSettingDialogListener
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

        val builder = AlertDialog.Builder(activity)
        builder.setView(createView())
            .setPositiveButton(R.string.add, DialogInterface.OnClickListener { dialogInterface, i ->
                dialogListener.onAddButton(this)
            })
            .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->
                dialogListener.onCancelButton(this)
            })
        return builder.create()
    }

    fun createView() : View {
        val activity = requireActivity()
        val inflater = activity.layoutInflater

        val view = inflater.inflate(R.layout.fragment_widget_parameter_setting, null) as ViewGroup

        viewModel.paramController.value?.paramsResId?.forEach {fieldResId ->

            val field = inflater.inflate(R.layout.field_widget_parameter_setting, view)
            val editText = field.field_widget_parameter_settings_text
            editText.hint = getString(fieldResId)
        }

        return view
    }

}
