package com.example.habittrackerproject.view

import android.content.Context

class Repository {

    fun getHabits(context: Context, callback: (List<Habit>) -> Unit) {
        ApiService.getHabits(context, callback)
    }

    fun addHabit(context: Context, habit: Habit) {
        ApiService.addHabit(context, habit)
    }

    fun updateHabit(context: Context, habit: Habit) {
        ApiService.updateHabit(context, habit)
    }
}