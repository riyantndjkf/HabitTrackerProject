package com.example.habittrackerproject.util

import android.content.Context
import androidx.room.Room
import com.example.habittrackerproject.database.AppDatabase

val DB_NAME = "habit_database"

@Volatile
private var INSTANCE: AppDatabase? = null

fun buildDb(context: Context): AppDatabase {
    return INSTANCE ?: synchronized(AppDatabase::class.java) {
        val instance = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DB_NAME
        ).build()
        INSTANCE = instance
        instance
    }
}
