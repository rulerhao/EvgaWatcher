package com.rulhouse.evgawatcher.methods.work_manager.use_cases

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.rulhouse.evgawatcher.methods.work_manager.repository.WorkManagerRepository

class GetPeriodWorkState(
    private val repository: WorkManagerRepository
) {
    operator fun invoke(): LiveData<List<WorkInfo>> {
        return repository.getPeriodWorkState()
    }
}