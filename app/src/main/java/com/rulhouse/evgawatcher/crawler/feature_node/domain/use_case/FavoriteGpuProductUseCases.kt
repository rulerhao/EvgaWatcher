package com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case

import com.oucare.bbt_oucare.feature_node.domain.use_case.AddFavoriteGpuProduct
import com.oucare.bbt_oucare.feature_node.domain.use_case.DeleteFavoriteGpuProduct
import com.oucare.bbt_oucare.feature_node.domain.use_case.GetFavoriteGpuProduct
import com.oucare.bbt_oucare.feature_node.domain.use_case.GetFavoriteGpuProducts

data class FavoriteGpuProductUseCases (
    val getFavoriteGpuProducts: GetFavoriteGpuProducts,
    val deleteFavoriteGpuProduct: DeleteFavoriteGpuProduct,
    val addFavoriteGpuProduct: AddFavoriteGpuProduct,
    val getFavoriteGpuProductByName: GetFavoriteGpuProductByName,
    val getFavoriteGpuProduct: GetFavoriteGpuProduct
)
