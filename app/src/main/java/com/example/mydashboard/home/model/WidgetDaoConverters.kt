package com.example.mydashboard.home.model

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class WidgetDaoConverters {

    @TypeConverter
    fun parametersToString(parameters: Map<String, String>) : String {
        val gson = GsonBuilder().create()
        return gson.toJson(parameters)
    }

    @TypeConverter
    fun stringToParameters(parametersStr: String) : Map<String, String> {
        val gson = GsonBuilder().create()
        return gson.fromJson(parametersStr, object : TypeToken<Map<String, String>>() {}.type)
    }

}