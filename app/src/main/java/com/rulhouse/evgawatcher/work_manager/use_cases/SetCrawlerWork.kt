package com.rulhouse.evgawatcher.work_manager.use_cases

import com.rulhouse.evgawatcher.work_manager.repository.WorkManagerRepository

class SetCrawlerWork (
    private val repository: WorkManagerRepository
) {
    operator fun invoke() {
        return repository.setCrawlerWork()
    }
}