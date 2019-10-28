package com.example.mydashboard.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mydashboard.model.user.User
import com.example.mydashboard.model.user.UserDao
import com.example.mydashboard.utils.SingletonHolder

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // As mentioned in the Android documentation, the singleton pattern is used for optimization concerns
    // See here for more details: https://developer.android.com/training/data-storage/room/index.html
    companion object : SingletonHolder<AppDatabase, Context> ({context ->
        Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Dashboard.db").build()
    })

    abstract fun userDao(): UserDao
}