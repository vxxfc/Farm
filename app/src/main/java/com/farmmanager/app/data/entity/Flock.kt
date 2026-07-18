package com.farmmanager.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flocks")
data class Flock(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val breed: String,
    val quantity: Int,
    val acquisitionDate: Long,
    val acquisitionCost: Double,
    val notes: String = ""
)
