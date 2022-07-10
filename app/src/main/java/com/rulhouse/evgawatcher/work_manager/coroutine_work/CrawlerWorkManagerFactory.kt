package com.rulhouse.evgawatcher.work_manager.coroutine_work

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.rulhouse.evgawatcher.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.notification_gpu_product_change.use_case.GetDifferentProductsUseCase

class CrawlerWorkManagerFactory(
    private val getDifferentProductsUseCase: GetDifferentProductsUseCase,
    private val notificationUseCase: NotificationUseCase
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker {
        return CrawlerWorker(appContext, workerParameters, getDifferentProductsUseCase, notificationUseCase)
    }
}