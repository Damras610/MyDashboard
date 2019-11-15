package com.example.mydashboard.model.widget

import com.example.mydashboard.model.user.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WidgetRepository @Inject constructor(
    private val userDao: UserDao,
    private val widgetDao: WidgetDao
) {

    fun getWidgetFromUser(username: String): Array<Widget> {
        val user = userDao.getUserByUsername(username)
        if (user == null)
            return emptyArray()

        return widgetDao.getWidgetsByUserId(user.id)
    }

    fun addWidgetToUser(username: String, serviceName: String, widgetName: String, parameters: Map<String, String> = emptyMap()): Long {
        val user = userDao.getUserByUsername(username)
        if (user == null)
            return (-1L)

        val widget = Widget(0, user.id, serviceName, widgetName, parameters)

        var insertedId : Long = -1L
        try {
            insertedId = widgetDao.insertWidget(widget)
        } finally {
            return (insertedId)
        }
    }
}