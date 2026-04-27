package com.example.habittrackerproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.habittrackerproject.model.Habit
import com.example.habittrackerproject.network.ApiService

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    val habitLD = MutableLiveData<List<Habit>>()
    fun refresh() {
        ApiService.getHabits(getApplication()) { habits ->
            habitLD.value = habits
        }
    }
    fun updateProgress(habit: Habit) {
        ApiService.updateHabit(getApplication(), habit)
        refresh()
    }
}