package com.example.mydashboard.widget.base

import com.example.mydashboard.widget.description.WidgetParamController
import com.example.mydashboard.widget.description.WidgetParamDescription

open class BaseParamController : WidgetParamController {

    override val params: Array<WidgetParamDescription> = emptyArray()
    override var invalidFields: Array<WidgetParamDescription> = emptyArray()

    override fun checkParameters(paramsValue: Map<WidgetParamDescription, String>) : Boolean {
        return true
    }
}