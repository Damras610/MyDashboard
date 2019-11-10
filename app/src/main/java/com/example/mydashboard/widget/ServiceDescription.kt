package com.example.mydashboard.widget

data class ServiceDescription(
    val nameResId: Int,
    val imageResId: Int,
    val descriptionResId: Int,
    val widgets: Array<WidgetDescription>
)