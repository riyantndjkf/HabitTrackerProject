package com.example.habittrackerproject.network

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

object VolleySingleton {
    private var requestQueue: RequestQueue? = null

    fun getInstance(context: Context): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.applicationContext)
        }
        return requestQueue!!
    }
}