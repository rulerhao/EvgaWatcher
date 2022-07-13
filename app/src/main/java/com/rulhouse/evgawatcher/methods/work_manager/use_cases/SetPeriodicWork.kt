package com.rulhouse.evgawatcher.methods.work_manager.use_cases

import com.rulhouse.evgawatcher.methods.work_manager.repository.WorkManagerRepository

class SetPeriodicWork (
    private val repository: WorkManagerRepository
) {
    operator fun invoke() {
        return repository.setPeriodicWork()
    }
}