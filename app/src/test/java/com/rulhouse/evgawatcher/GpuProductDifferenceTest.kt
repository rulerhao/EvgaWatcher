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
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
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
    var hiltRule = Hilt(this)

    @Inject
    lateinit var getDifferentProductsUseCase: GetDifferentProductsUseCase

    @Before
    fun setup() {

        hiltRule.inject()

    }

    @Test
    private fun test1() {
        runBlocking {
            val differenceProducts = getDifferentProductsUseCase.getDifferenceProducts()
            assertThat(differenceProducts, Is.`is`(emptyList()))
        }
    }

    @Test
    private fun testPriceGoesUp() {
        runBlocking {
            val differenceProducts = getDifferentProductsUseCase.getDifferenceProducts()
            assertThat(differenceProducts, Is.`is`(emptyList()))
        }
    }

}