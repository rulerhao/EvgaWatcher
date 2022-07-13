package com.rulhouse.evgawatcher.methods.work_manager.use_cases

import androidx.work.ListenableWorker
import com.rulhouse.evgawatcher.methods.work_manager.repository.WorkManagerRepository

class SetWork (
    private val repository: WorkManagerRepository
) {
    operator fun invoke(workerClass: Class<out ListenableWorker>) {
        return repository.setWork(workerClass)
    }
}