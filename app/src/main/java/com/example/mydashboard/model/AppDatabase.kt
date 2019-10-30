package com.example.mydashboard.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mydashboard.model.user.User
import com.example.mydashboard.model.user.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
