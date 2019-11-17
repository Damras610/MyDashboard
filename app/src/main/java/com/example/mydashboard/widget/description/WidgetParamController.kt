package com.example.mydashboard.widget.description

interface WidgetParamController {

    val params: Array<WidgetParamDescription>
    var invalidFields: Array<WidgetParamDescription>

    fun checkParameters(paramsValue: Map<WidgetParamDescription, String>): Boolean
}