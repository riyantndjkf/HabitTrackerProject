package com.example.habittrackerproject.model
import com.google.gson.annotations.SerializedName

data class Habit(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("target") val target: Int,
    @SerializedName("progress") var progress: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("status") var status: String
)