package com.example.habittrackerproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.habittrackerproject.model.Habit
import com.example.habittrackerproject.model.User

@Database(
    entities = [User::class, Habit::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun habitDao(): HabitDao


}