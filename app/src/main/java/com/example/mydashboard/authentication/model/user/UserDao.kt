package com.example.mydashboard.authentication.model.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * from user WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): User?

    @Query("SELECT * from user WHERE username = :username AND password = :password LIMIT 1")
    fun getUserByCredentials(username: String, password: String): User?

    @Insert
    fun insertUser(user: User): Long
}