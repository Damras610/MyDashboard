package com.example.mydashboard.home.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.mydashboard.authentication.model.user.User

@Entity(foreignKeys = [ForeignKey(entity = User::class,
    parentColumns = ["id"],
    childColumns = ["user_id"],
    onDelete = ForeignKey.CASCADE)])
data class Widget (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "user_id") val userId: Long,
    @ColumnInfo(name = "service_name") val serviceName: String,
    @ColumnInfo(name = "widget_name") val widgetName: String,
    val parameters: Map<String, String>
)