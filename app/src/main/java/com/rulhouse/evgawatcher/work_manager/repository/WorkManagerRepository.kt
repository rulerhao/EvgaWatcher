package com.rulhouse.evgawatcher.work_manager.repository

import androidx.annotation.NonNull
import androidx.work.ListenableWorker

interface WorkManagerRepository {
    fun setWork(workerClass: Class<out ListenableWorker>)

    fun setCrawlerWork()

    fun cancelCrawlerWork()

    fun setPeriodicWork()

    fun cancelPeriodicWork()
}