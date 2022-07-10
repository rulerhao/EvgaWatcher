package com.rulhouse.evgawatcher.favorite_products.domain.use_case

import com.oucare.bbt_oucare.feature_node.domain.use_case.GetFavoriteGpuProduct

data class FavoriteGpuProductUseCases (
    val getFavoriteGpuProductsFlow: GetFavoriteGpuProductsFlow,
    val getFavoriteGpuProducts: GetFavoriteGpuProducts,
    val deleteFavoriteGpuProduct: DeleteFavoriteGpuProduct,
    val addFavoriteGpuProduct: AddFavoriteGpuProduct,
    val getFavoriteGpuProductFlowByName: GetFavoriteGpuProductFlowByName,
    val getFavoriteGpuProductByName: GetFavoriteGpuProductByName,
    val getFavoriteGpuProduct: GetFavoriteGpuProduct
)
