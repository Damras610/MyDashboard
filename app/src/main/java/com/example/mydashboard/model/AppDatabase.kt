package com.example.mydashboard.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mydashboard.model.user.User
import com.example.mydashboard.model.user.UserDao
import com.example.mydashboard.model.widget.Widget
import com.example.mydashboard.model.widget.WidgetDao
import com.example.mydashboard.model.widget.WidgetDaoConverters

@Database(entities = [User::class, Widget::class], version = 1)
@TypeConverters(WidgetDaoConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun widgetDao(): WidgetDao
}
