package com.rulhouse.evgawatcher.work_manager.repository

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.work.ListenableWorker
import androidx.work.WorkInfo

interface WorkManagerRepository {
    fun setWork(workerClass: Class<out ListenableWorker>)

    fun setCrawlerWork()

    fun cancelCrawlerWork()

    fun setPeriodicWork()

    fun cancelPeriodicWork()

    fun getPeriodWorkState(): LiveData<List<WorkInfo>>
}