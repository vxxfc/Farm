package com.farmmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val dateTime: Long,
    val notes: String = "",
    val isCompleted: Boolean = false
)
