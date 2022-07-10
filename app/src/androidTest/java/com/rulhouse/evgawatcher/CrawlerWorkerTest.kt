package com.rulhouse.evgawatcher

import android.content.Context
import android.util.Log
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import com.rulhouse.evgawatcher.methods.work_manager.coroutine_work.CrawlerWorkManagerFactory
import com.rulhouse.evgawatcher.methods.work_manager.coroutine_work.CrawlerWorker
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class CrawlerWorkerTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context

    @Inject
    lateinit var crawlerUseCases: CrawlerUseCases

    @Inject
    lateinit var getDifferentProductsUseCase: GetDifferentProductsUseCase
    @Inject
    lateinit var notificationUseCase: NotificationUseCase

    @Before
    fun setup() {
        hiltRule.inject()

        context = InstrumentationRegistry.getInstrumentation().targetContext

        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        // Initialize WorkManager for instrumentation tests.
        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }

    @Test
    fun testCrawlerWorker() {
        val worker = TestListenableWorkerBuilder<CrawlerWorker>(context)
            .setWorkerFactory(CrawlerWorkManagerFactory(getDifferentProductsUseCase, notificationUseCase))
            .build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(ListenableWorker.Result.success()))
        }
    }
}