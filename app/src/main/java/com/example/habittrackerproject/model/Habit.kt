package com.example.habittrackerproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var title: String,
    var description: String,

    var target: String,

    var unit: String,

    var progress: String,

    var icon: String,
    var status: String
)