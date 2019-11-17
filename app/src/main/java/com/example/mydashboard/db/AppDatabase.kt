package com.example.mydashboard.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mydashboard.authentication.model.user.User
import com.example.mydashboard.authentication.model.user.UserDao
import com.example.mydashboard.home.model.Widget
import com.example.mydashboard.home.model.WidgetDao
import com.example.mydashboard.home.model.WidgetDaoConverters

@Database(entities = [User::class, Widget::class], version = 1)
@TypeConverters(WidgetDaoConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun widgetDao(): WidgetDao
}
