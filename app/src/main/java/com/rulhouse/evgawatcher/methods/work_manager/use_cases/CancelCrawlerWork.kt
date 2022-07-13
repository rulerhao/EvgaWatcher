package com.rulhouse.evgawatcher.methods.work_manager.use_cases

import com.rulhouse.evgawatcher.methods.work_manager.repository.WorkManagerRepository

class CancelCrawlerWork (
    private val repository: WorkManagerRepository
) {
    operator fun invoke() {
        return repository.cancelCrawlerWork()
    }
}