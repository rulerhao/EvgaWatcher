package com.rulhouse.evgawatcher.work_manager.coroutine_work

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CrawlerWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val crawlerUseCases: CrawlerUseCases
): CoroutineWorker(appContext, workerParams){

    override suspend fun doWork(): Result {

        Log.d("TestWorker", "GpuItems = ${crawlerUseCases.getGpuItems()}")

        return Result.success()
    }
}