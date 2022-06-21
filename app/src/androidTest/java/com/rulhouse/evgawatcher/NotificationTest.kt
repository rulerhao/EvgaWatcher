package com.rulhouse.evgawatcher

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.rulhouse.evgawatcher.notification.DifferentProductsNotification
import org.junit.Before
import org.junit.Test

class NotificationTest {

    private lateinit var context: Context

    @Before
    fun setup() {

        context = InstrumentationRegistry.getInstrumentation().targetContext

    }

    @Test
    fun test() {
        DifferentProductsNotification(context).doNotify(context)
    }
}