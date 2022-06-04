package com.oucare.bbt_oucare.feature_node.domain.use_case

data class FavoriteGpuProductUseCases (
    val getFavoriteGpuProducts: GetFavoriteGpuProducts,
    val deleteFavoriteGpuProduct: DeleteFavoriteGpuProduct,
    val addFavoriteGpuProduct: AddFavoriteGpuProduct,
    val getFavoriteGpuProduct: GetFavoriteGpuProduct
)
