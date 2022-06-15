package com.rulhouse.evgawatcher.work_manager.use_cases

data class WorkManagerUseCases (
    val setWork: SetWork,
    val setPeriodicWork: SetPeriodicWork,
    val cancelPeriodicWork: CancelPeriodicWork,
    val setCrawlerWork: SetCrawlerWork,
    val cancelCrawlerWork: CancelCrawlerWork
)