package com.example.mydashboard.widget.youtube.configuration

import com.example.mydashboard.R
import com.example.mydashboard.widget.base.configuration.BaseWidgetParamController
import com.example.mydashboard.widget.description.ServiceDescription
import com.example.mydashboard.widget.description.WidgetDescription

val SERVICE_DESCRIPTION = ServiceDescription(
    nameResId = R.string.service_youtube_name,
    imageResId = R.drawable.im_service_youtube,
    descriptionResId = R.string.service_youtube_description,
    widgets = arrayOf(
        WidgetDescription(
            nameResId = R.string.widget_youtube_video_name,
            descriptionResId = R.string.widget_youtube_video_description,
            widgetParamController = BaseWidgetParamController(),
            dbNameResId = R.string.db_widget_youtube_video
        ),
        WidgetDescription(
            nameResId = R.string.widget_youtube_channel_name,
            descriptionResId = R.string.widget_youtube_channel_description,
            widgetParamController = BaseWidgetParamController(),
            dbNameResId = R.string.db_widget_youtube_channel
        ),
        WidgetDescription(
            nameResId = R.string.widget_youtube_trending_name,
            descriptionResId = R.string.widget_youtube_trending_description,
            widgetParamController = BaseWidgetParamController(),
            dbNameResId = R.string.db_widget_youtube_trending
        )
    ),
    dbNameResId = R.string.db_service_youtube
)