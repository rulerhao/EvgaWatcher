package com.rulhouse.evgawatcher.methods.notification_gpu_product_change.impl

import com.rulhouse.evgawatcher.methods.crawler.crawler.use_cases.CrawlerUseCases
import com.rulhouse.evgawatcher.methods.favorite_products.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.repository.GetDifferentProductsRepository
import kotlinx.coroutines.flow.Flow

class GetDifferentProductsImpl(
    private val crawlerUseCases: CrawlerUseCases,
    private val favoriteGpuProductUseCases: FavoriteGpuProductUseCases
): GetDifferentProductsRepository {

    override suspend fun getDifference(): List<ProductsDifference> {
        return NotificationGpuProductChange(crawlerUseCases, favoriteGpuProductUseCases).getDifferenceProducts()
    }

    override suspend fun getProductsDifferenceWithReason(): List<ProductsDifferenceWithReason> {
        return NotificationGpuProductChange(crawlerUseCases, favoriteGpuProductUseCases).getProductsDifferenceWithReason()
    }

    override suspend fun getProductsDifferenceWithReasonFlow(): Flow<List<ProductsDifferenceWithReason>> {
        return NotificationGpuProductChange(crawlerUseCases, favoriteGpuProductUseCases).getProductsDifferenceWithReasonFlow()
    }

}