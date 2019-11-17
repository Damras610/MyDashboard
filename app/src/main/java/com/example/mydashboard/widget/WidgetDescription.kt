package com.example.mydashboard.widget

data class WidgetDescription(
    val nameResId: Int,
    val descriptionResId: Int,
    val widgetParamController: WidgetParamController,
    val dbNameResId: Int

)