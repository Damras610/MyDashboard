package com.example.mydashboard.widget.twitch.configuration

import com.example.mydashboard.R
import com.example.mydashboard.widget.base.configuration.BaseWidgetParamController
import com.example.mydashboard.widget.description.ServiceDescription
import com.example.mydashboard.widget.description.WidgetDescription

val SERVICE_DESCRIPTION = ServiceDescription(
    nameResId = R.string.service_twitch_name,
    imageResId = R.drawable.im_service_twitch,
    descriptionResId = R.string.service_twitch_description,
    widgets = arrayOf(
        WidgetDescription(
            nameResId = R.string.widget_twitch_trending_name,
            descriptionResId = R.string.widget_twitch_trending_description,
            widgetParamController = BaseWidgetParamController(),
            dbNameResId = R.string.db_widget_twitch_trending
        )
    ),
    dbNameResId = R.string.db_service_twitch
)