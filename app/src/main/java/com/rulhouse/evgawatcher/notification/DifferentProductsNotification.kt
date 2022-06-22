package com.rulhouse.evgawatcher.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.rulhouse.evgawatcher.R

class DifferentProductsNotification(context: Context) {

    private val notificationId1 = 55
    private val notificationId2 = 56

    private val CHANNEL_ID = "Channel id"

    val GROUP_KEY_WORK_EMAIL = context.packageName

    val SUMMARY_ID = 0

    var builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("有東西改變了")
        .setContentText("改變快樂")
        .setGroup(GROUP_KEY_WORK_EMAIL)

    val newMessageNotification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentTitle("有東西改變了2")
        .setContentText("改變快樂2")
        .setGroup(GROUP_KEY_WORK_EMAIL)

    val summaryNotification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("有東西改變了3")
        //set content text to support devices running API level < 24
        .setContentText("Two new messages")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        //build summary info into InboxStyle template
        .setStyle(NotificationCompat.InboxStyle()
            .addLine("Alex Faarborg Check this out")
            .addLine("Jeff Chang Launch Party")
            .setBigContentTitle("2 new messages")
            .setSummaryText("janedoe@example.com"))
        //specify which group this notification belongs to
        .setGroup(GROUP_KEY_WORK_EMAIL)
        //set this notification as the summary for the group
        .setGroupSummary(true)

    private fun createNotificationChannel(context : Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notifyStart(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId1, builder.build())
        }
    }

    fun notifyStart2(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId2, newMessageNotification.build())
        }
    }

    fun notifyStartSummary(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(SUMMARY_ID, summaryNotification.build())
        }
    }

    fun notifyStartAll(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            notify(NotificationID.getID(), builder.build())
            notify(NotificationID.getID(), newMessageNotification.build())
            notify(SUMMARY_ID, summaryNotification.build())
        }
    }

    fun doNotify(context: Context) {
        createNotificationChannel(context)

        notifyStartAll(context)
    }
}