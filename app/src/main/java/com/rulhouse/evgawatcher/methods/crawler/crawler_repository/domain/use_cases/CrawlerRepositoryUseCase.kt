package com.rulhouse.evgawatcher.methods.crawler.crawler_repository.domain.use_cases

data class CrawlerRepositoryUseCase (
    val getCrawlerRepositoryFlow: GetCrawlerRepositoryFlow,
    val insertCrawlerRepository: InsertCrawlerRepository,
    val insertCrawlerRepositoryProduct: InsertCrawlerRepositoryProduct,
    val getCrawlerRepositoryProductByName: GetCrawlerRepositoryProductByName
)