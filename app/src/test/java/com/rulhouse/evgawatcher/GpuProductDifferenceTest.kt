package com.rulhouse.evgawatcher

import android.content.Context
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.impl.utils.SynchronousExecutor
import androidx.work.testing.TestListenableWorkerBuilder
import androidx.work.testing.WorkManagerTestInitHelper
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.work_manager.coroutine_work.CrawlerWorkManagerFactory
import com.rulhouse.evgawatcher.work_manager.coroutine_work.CrawlerWorker
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class GpuProductDifferenceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var context: Context

    @Inject
    lateinit var crawlerUseCases: CrawlerUseCases

    @Before
    fun setup() {

        hiltRule.inject()
    }

    @Test
    fun testSleepWorker() {
        val worker = TestListenableWorkerBuilder<CrawlerWorker>(context)
            .setWorkerFactory(CrawlerWorkManagerFactory(crawlerUseCases))
            .build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result, Is.`is`(ListenableWorker.Result.success()))
        }
    }
}