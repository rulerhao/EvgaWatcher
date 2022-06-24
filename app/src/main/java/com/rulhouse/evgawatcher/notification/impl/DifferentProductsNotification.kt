package com.rulhouse.evgawatcher.notification.impl

import android.app.Notification
import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.notification.NotificationID
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.renew_favorite_products.RenewFavoriteProductsBroadcastReceiver
import java.io.Serializable

class DifferentProductsNotification {

    private val CHANNEL_ID = "Evga watcher channel id"

    private val GROUP_KEY = "Evga watcher group key"

    private val SUMMARY_ID = 0

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

    private fun notifyStart(context: Context, notifyId: Int, notification: Notification, sumNotification: Notification) {
        with(NotificationManagerCompat.from(context)) {
            notify(notifyId, notification)
            notify(SUMMARY_ID, sumNotification)
        }
    }

    fun doNotify(context: Context, id: Int, productsDifference: ProductsDifference) {
        createNotificationChannel(context)

        val pendingIntent = getPendingIntent(context, productsDifference)
        val notification = getNotification(context = context, pendingIntent = pendingIntent)
        val summaryNotification = getSummaryNotification(context = context)

        notifyStart(context, id, notification, summaryNotification)
    }

    private fun getPendingIntent(context: Context, productsDifference: ProductsDifference): PendingIntent {
        val snoozeIntent = Intent(context, RenewFavoriteProductsBroadcastReceiver::class.java).apply {
            putExtra(EXTRA_NOTIFICATION_ID, productsDifference)
        }

        return PendingIntent.getBroadcast(context, 0, snoozeIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun getNotification(context: Context, pendingIntent: PendingIntent): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("有東西改變了2")
            .setContentText("改變快樂2")
            .addAction(R.drawable.ic_launcher_foreground, "SNOOZE",
                pendingIntent)
            .setGroup(GROUP_KEY)
            .build()
    }

    private fun getSummaryNotification(context: Context): Notification {
        return NotificationCompat.Builder(context, CHANNEL_ID)
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
            .setGroup(GROUP_KEY)
            //set this notification as the summary for the group
            .setGroupSummary(true)
            .build()
    }
}