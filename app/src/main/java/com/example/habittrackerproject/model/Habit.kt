package com.example.habittrackerproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "habit_table")
data class Habit(
    @PrimaryKey
    @SerializedName("id") var id: String,

    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,

    @SerializedName("target") var target: String,

    @SerializedName("unit") var unit: String,

    @SerializedName("progress") var progress: String,

    @SerializedName("icon") var icon: String,
    @SerializedName("status") var status: String
)