package com.example.habittrackerproject.view

import com.example.habittrackerproject.model.Habit

interface HabitListener {
    fun onMinusClick(habit: Habit)
    fun onPlusClick(habit: Habit)

}