package com.example.mydashboard.widget.description

open class BaseParamController : WidgetParamController {

    override val params: Array<WidgetParamDescription> = emptyArray()
    override var invalidFields: Array<WidgetParamDescription> = emptyArray()

    override fun checkParameters(paramsValue: Map<WidgetParamDescription, String>) : Boolean {
        return true
    }
}