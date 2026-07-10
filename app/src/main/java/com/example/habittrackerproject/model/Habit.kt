package com.example.habittrackerproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "habits")
data class Habit(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title") var title: String,
    @SerializedName("description") var description: String,

    @SerializedName("target") var target: String,

    @SerializedName("unit") var unit: String,

    @SerializedName("progress") var progress: String,

    @SerializedName("icon") var icon: String,
    @SerializedName("status") var status: String
)