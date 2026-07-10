package com.example.habittrackerproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habittrackerproject.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(
        username: String,
        password: String
    ): User?

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}