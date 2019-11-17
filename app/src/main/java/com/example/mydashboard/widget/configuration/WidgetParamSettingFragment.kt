package com.example.mydashboard.widget.configuration

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import com.example.mydashboard.R
import com.example.mydashboard.widget.description.WidgetParamDescription
import com.google.android.material.textfield.TextInputLayout
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.field_widget_parameter_setting.view.*
import timber.log.Timber
import javax.inject.Inject


class WidgetParamSettingFragment(
    val serviceId: Int,
    val widgetId: Int,
    val dialogListener: WidgetParamSettingDialogListener
) : DialogFragment() {

    // View Model
    @Inject
    lateinit var viewModel: WidgetParamSettingViewModel

    lateinit var layout: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        viewModel.loadParamController(serviceId, widgetId)

        val builder = AlertDialog.Builder(activity)
        loadView()
        builder.setView(layout)
            .setTitle(R.string.widget_config_setup_widget)
            .setPositiveButton(R.string.add, null)
            .setNegativeButton(R.string.cancel, null)

        val dialog = builder.create()

        // The listeners are set after building the dialog to prevent autodismissing
        // of the dialog on action button press
        dialog.setOnShowListener {
            val d: AlertDialog = it as AlertDialog
            val posButton = d.getButton(AlertDialog.BUTTON_POSITIVE);
            posButton.setOnClickListener {
                dialogListener.onAddButton(this)
            }
            val negButton = d.getButton(AlertDialog.BUTTON_NEGATIVE);
            negButton.setOnClickListener {
                dialogListener.onCancelButton(this)
                d.dismiss()
            }
        }

        return dialog
    }

    fun getParamsValue() :  Map<WidgetParamDescription, String> {

        val paramsDescription = viewModel.paramController.value?.params ?: return emptyMap()
        val paramsValue = mutableMapOf<WidgetParamDescription, String>()

        layout.children.forEach lit@{field ->
            val editText = field.field_widget_parameter_settings_text
            paramsDescription.forEach { paramDescription ->
                if (paramDescription.nameResId == editText.tag) {
                    paramsValue[paramDescription] = editText.text.toString()
                    return@lit
                }
            }
        }
        return paramsValue
    }

    fun checkParamsValue() : Boolean {
        val paramsValue = getParamsValue()
        val widgetParamController = viewModel.paramController.value ?: return true
        if (widgetParamController.checkParameters(paramsValue)) {
            Timber.d("Hello")
            return true
        }
        else {
            Timber.d("Hella")
            val invalidFields = widgetParamController.invalidFields
            invalidFields.forEach lit@{invalidField ->
                Timber.d("Helli")
                layout.children.forEach {
                    Timber.d("Hellu")
                    val field = it as TextInputLayout
                    val editText = field.field_widget_parameter_settings_text
                    if (invalidField.nameResId == editText.tag) {
                        Timber.d("Helly")
                        field.error = getString(R.string.widget_config_invalid_field)
                        return@lit
                    }
                }
            }
            return false
        }
    }

    private fun loadView() : ViewGroup {
        val activity = requireActivity()
        val inflater = activity.layoutInflater

        layout = inflater.inflate(R.layout.fragment_widget_parameter_setting, null) as ViewGroup

        viewModel.paramController.value?.params?.forEach {param ->

            val field = inflater.inflate(R.layout.field_widget_parameter_setting, layout)
            val editText = field.field_widget_parameter_settings_text
            editText.hint = getString(param.nameResId)
            editText.tag = param.nameResId
        }

        return layout
    }

}
