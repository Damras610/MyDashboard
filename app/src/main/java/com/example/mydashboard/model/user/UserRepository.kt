package com.example.mydashboard.model.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getUser(username: String): User {
        return userDao.getUserByUsername(username)
    }

    fun saveUser(username: String, password: String, email: String = ""): Boolean {
        val insertSucceeded = userDao.insertUser(UserInfo(username, password, email))
        return (insertSucceeded != -1L)
    }
}