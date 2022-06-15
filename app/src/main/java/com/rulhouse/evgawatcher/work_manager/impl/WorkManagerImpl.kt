package com.rulhouse.evgawatcher.work_manager.impl

import android.content.Context
import androidx.work.*
import com.rulhouse.evgawatcher.work_manager.coroutine_work.CrawlerWorker
import com.rulhouse.evgawatcher.work_manager.factory.WorkManagerFactory
import com.rulhouse.evgawatcher.work_manager.repository.WorkManagerRepository
import java.util.concurrent.TimeUnit

class WorkManagerImpl(
    private val context: Context,
    private val workManagerFactory: WorkManagerFactory
): WorkManagerRepository {

    override fun setWork(workerClass: Class<out ListenableWorker>) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(workerClass, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            workManagerFactory.workManagerUniqueName,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    override fun setCrawlerWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(CrawlerWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            CrawlerWorker::class.java.simpleName,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    override fun cancelCrawlerWork() {
        WorkManager.getInstance(context).cancelUniqueWork(
            CrawlerWorker::class.java.simpleName
        )
    }

    override fun setPeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val request = PeriodicWorkRequest.Builder(CrawlerWorker::class.java, 15, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            workManagerFactory.workManagerUniqueName,
            ExistingPeriodicWorkPolicy.REPLACE,
            request
        )
    }

    override fun cancelPeriodicWork() {
        WorkManager.getInstance(context).cancelUniqueWork(
            workManagerFactory.workManagerUniqueName
        )
    }

}