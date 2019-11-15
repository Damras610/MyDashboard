package com.example.mydashboard.widget

class BaseParamController : WidgetParamController {

    override val paramsResId: Array<Int>

    init {
        paramsResId = emptyArray()
    }

    override fun checkParameters(vararg ts: String) : Boolean {
        return true
    }
}