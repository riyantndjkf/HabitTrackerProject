package com.example.habittrackerproject

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.habittrackerproject.view.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        if (!MainActivity.isVisible) {
            val title = message.notification?.title ?: "N/A"
            val body = message.notification?.body ?: "N/A"
            showNotification(title, body)
        }
        else {
            val title = message.data["title"] ?: "N/A"
            val body = message.data["body"] ?: "N/A"

            Log.d("FCM", "title, body = $title, $body")

            // Forward to the UI
            val intent = Intent("FCM_MESSAGE")
                .putExtra("title", title)
                .putExtra("body", body)
            sendBroadcast(intent)
        }
    }
    private fun showNotification(title: String, body: String) {
        val channelId = "fcm_default_channel"

        // Membangun Notification Channel (Dibutuhkan untuk Android versi Oreo ke atas)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        // Menampilkan notifikasi ke layar HP
        notificationManager.notify(0, notificationBuilder.build())
    }
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM", "New Token: $token")
    }
}
