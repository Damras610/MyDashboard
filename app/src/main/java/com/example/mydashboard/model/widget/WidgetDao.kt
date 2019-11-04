package com.example.mydashboard.model.widget

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WidgetDao {

    @Query("SELECT * FROM widget WHERE user_id = :userId")
    fun getWidgetsByUserId(userId: Long): Array<Widget>

    @Insert
    fun insertWidget(widget: Widget): Long
}