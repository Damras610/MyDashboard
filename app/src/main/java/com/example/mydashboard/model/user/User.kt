package com.example.mydashboard.model.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey private val id: Int,
    private val username: String,
    private val password: String,
    private val email: String = ""
)