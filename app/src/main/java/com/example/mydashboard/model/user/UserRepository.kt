package com.example.mydashboard.model.user

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    fun getUser(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    fun getUserByCredential(username: String, password: String) : User? {
        return userDao.getUserByCredentials(username, password)
    }

    fun saveNewUser(username: String, password: String, email: String = ""): Long {
        var insertedId : Long = -1L
        try {
            insertedId = (userDao.insertUser(UserInfo(username, password, email)))
        } finally {
            return (insertedId)
        }
    }
}