package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductItem(
    gpuProduct: GpuProduct = GpuProduct(
        name = "EVGA GeForce RTX 3090 Ti FTW3 ULTRA HYBRID GAMING, 24G-P5-4988-KR, 24GB GDDR6X, iCX3, HYBRID Cooler, ARGB LED, Backplate, Free eLeash",
        imgUrl = "https://images.evga.com/products/gallery/png/24G-P5-4988-KR_MD_1.png",
        serial = "24G-P5-4988-KR",
        canBeBought = false,
        statement = listOf(),
        uri = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
        limitedNumber = "",
        price = 20000,
        warranty = "全球保固： 3 年",
        favorite = false
    ),
    onClick: (GpuProduct) -> Unit,
    onFavoriteClick: (GpuProduct) -> Unit,
    onStoreClick: (GpuProduct) -> Unit
) {

    val imageHeight = remember { mutableStateOf(IntSize(0, 0)) }

    Card(
        modifier = Modifier
            .clickable {
                onClick(gpuProduct)
            },
        shape = MaterialTheme.shapes.extraLarge
    ) {
        GpuImage(
            imgUrl = gpuProduct.imgUrl,
            size = imageHeight.value
        )
        Column(
            modifier = Modifier
                .padding(8.dp)
                .onSizeChanged {
                    imageHeight.value = it
                }
        ) {
            Title(text = gpuProduct.name)
            Column(
                modifier = Modifier
                    .align(Alignment.End),
                horizontalAlignment = Alignment.End,
            ) {
                Price(price = gpuProduct.price)
                StoreAndFavorite(
                    favorite = gpuProduct.favorite,
                    onFavoriteClick = {
                        onFavoriteClick(gpuProduct)
                    },
                    buyable = gpuProduct.canBeBought,
                    onStoreClick = {
                        onStoreClick(gpuProduct)
                    }
                )
            }
        }
    }
}