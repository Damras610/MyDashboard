package com.example.mydashboard.widget.base.configuration

import com.example.mydashboard.widget.description.WidgetParamController
import com.example.mydashboard.widget.description.WidgetParamDescription

open class BaseWidgetParamController : WidgetParamController {

    override val params: Array<WidgetParamDescription> = emptyArray()
    override var invalidFields: Array<WidgetParamDescription> = emptyArray()

    override fun checkParameters(paramsValue: Map<WidgetParamDescription, String>) : Boolean {
        return true
    }
}