package com.example.mydashboard.widget

interface WidgetParamController {

    val paramsResId: Array<Int>

    fun checkParameters(vararg ts: String): Boolean
}