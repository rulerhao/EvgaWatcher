package com.rulhouse.evgawatcher

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.rulhouse.evgawatcher.notification.impl.DifferentProductsNotification
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
//        DifferentProductsNotification(context).doNotify(context)
    }
}