package com.rulhouse.evgawatcher.crawler.crawler_repository.domain.use_cases

data class CrawlerRepositoryUseCase (
    val getCrawlerRepositoryFlow: GetCrawlerRepositoryFlow,
    val insertCrawlerRepository: InsertCrawlerRepository
)