package com.example.habittrackerproject.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.habittrackerproject.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ApiService {

    private const val BASE_URL = "http://10.0.2.2/habit_api/"

    fun getHabits(context: Context, callback: (List<Habit>) -> Unit) {
        val url = BASE_URL + "getHabits.php"

        val request = StringRequest(
            Request.Method.GET, url,
            { response ->
                val gson = Gson()
                val type = object : TypeToken<List<Habit>>() {}.type
                val habits: List<Habit> = gson.fromJson(response, type)

                callback(habits)
            },
            { error ->
                error.printStackTrace()
            })

        VolleySingleton.getInstance(context).add(request)
    }

    fun addHabit(context: Context, habit: Habit) {
        val url = BASE_URL + "addHabit.php"

        val request = object : StringRequest(Method.POST, url,
            Response.Listener { },
            Response.ErrorListener { it.printStackTrace() }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["title"] = habit.title
                params["description"] = habit.description
                params["target"] = habit.target.toString()
                params["progress"] = "0"
                params["icon"] = habit.icon
                params["status"] = "In Progress"
                return params
            }
        }

        VolleySingleton.getInstance(context).add(request)
    }

    fun updateHabit(context: Context, habit: Habit) {
        val url = BASE_URL + "updateHabit.php"

        val request = object : StringRequest(Method.POST, url,
            Response.Listener { },
            Response.ErrorListener { it.printStackTrace() }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()

                params["id"] = habit.id

                val status = if (habit.progress >= habit.target) {
                    "Completed"
                } else {
                    "In Progress"
                }

                params["progress"] = habit.progress.toString()
                params["status"] = status

                return params
            }
        }

        VolleySingleton.getInstance(context).add(request)
    }
}