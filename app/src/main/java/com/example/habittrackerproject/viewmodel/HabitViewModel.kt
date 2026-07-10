package com.example.habittrackerproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.habittrackerproject.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.habittrackerproject.model.Habit
import com.example.habittrackerproject.network.ApiService

class HabitViewModel(application: Application) : AndroidViewModel(application) {

    val habitLD = MutableLiveData<List<Habit>>()
    val db = AppDatabase.getDatabase(application)
    val habitDao = db.habitDao()

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val habits = habitDao.getAllHabits()
            launch(Dispatchers.Main) {
                habitLD.value = habits
            }
        }
    }
    fun updateProgress(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.update(habit)
            val habits = habitDao.getAllHabits()
            launch(Dispatchers.Main) {
                habitLD.value = habits
            }
        }
    }
    fun addHabit(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.insert(habit)
            val habits = habitDao.getAllHabits()
            launch(Dispatchers.Main) {
                habitLD.value = habits
            }
        }
    }
    fun updateHabitRoom(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            habitDao.update(habit)
        }
    }
}