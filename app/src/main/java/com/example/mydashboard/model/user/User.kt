package com.example.mydashboard.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["username"], unique = true)])
data class User (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val username: String,
    val password: String,
    @ColumnInfo(defaultValue = "")
    val email: String
)