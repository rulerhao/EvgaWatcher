package com.rulhouse.evgawatcher.methods.work_manager.use_cases

data class WorkManagerUseCases (
    val setWork: SetWork,
    val setPeriodicWork: SetPeriodicWork,
    val cancelPeriodicWork: CancelPeriodicWork,
    val setCrawlerWork: SetCrawlerWork,
    val cancelCrawlerWork: CancelCrawlerWork,
    val getPeriodWorkStateLiveData: GetPeriodWorkState
)