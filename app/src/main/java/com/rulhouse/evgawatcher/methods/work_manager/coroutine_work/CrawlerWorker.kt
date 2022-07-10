package com.rulhouse.evgawatcher.methods.work_manager.coroutine_work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rulhouse.evgawatcher.methods.notification.use_case.NotificationUseCase
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferentProductsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CrawlerWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getDifferentProductsUseCase: GetDifferentProductsUseCase,
    private val notificationUseCase: NotificationUseCase
): CoroutineWorker(appContext, workerParams){

    override suspend fun doWork(): Result {

        val productsDifference = getDifferentProductsUseCase.getDifferenceProducts()
        notificationUseCase.notificationDifferentProducts(productsDifference)

        return Result.success()
    }
}