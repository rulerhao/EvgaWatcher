package com.rulhouse.evgawatcher.methods.notification.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.rulhouse.evgawatcher.MainActivity
import com.rulhouse.evgawatcher.presentation.Screen

class NotificationReceiver : BroadcastReceiver() {

    val extraName = "notification receiver extra name"

    val screen = Screen.RemindersScreen.route

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("NotificationReceiver", "OnReceive")
        val activityIntent = Intent(context, MainActivity::class.java)
        activityIntent.putExtra(extraName, screen)
        activityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(activityIntent)
    }

}