package com.example.habittrackerproject.model

data class Habit(
    val id: String,
    val title: String,
    val description: String,
    val target: Int,
    var progress: Int,
    val icon: String,
    var status: String
)