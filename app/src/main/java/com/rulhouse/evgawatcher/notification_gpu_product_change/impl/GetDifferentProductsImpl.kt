package com.rulhouse.evgawatcher.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.favorite_products.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.notification_gpu_product_change.repository.GetDifferentProductsRepository
import javax.inject.Inject

class GetDifferentProductsImpl(
    private val crawlerUseCases: CrawlerUseCases,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
): GetDifferentProductsRepository {

    override suspend fun getDifference(): List<ProductsDifference> {
        return NotificationGpuProductChange(crawlerUseCases, favoriteGpuProductUseCases).getDifferenceProducts()
    }
}