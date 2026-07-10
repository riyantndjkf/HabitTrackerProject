package com.example.habittrackerproject.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    fun addHabit(habit: Habit) {
        ApiService.addHabit(getApplication(), habit)
        refresh()
    }

    fun updateHabitRoom(habit: Habit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Memanggil fungsi buildDb buatan Anggota 1 dari package util
            // dan menjalankan @Update dari DOA. (Dummy implementasi agar tidak missing import saat dikerjakan teman)
            // val db = com.example.habittrackerproject.util.buildDb(getApplication())
            // db.habitDao().updateHabit(habit)
        }
    }
}