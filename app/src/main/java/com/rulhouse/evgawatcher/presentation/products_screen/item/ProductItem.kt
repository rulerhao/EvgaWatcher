package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        url = "https://tw.evga.com/Products/Product.aspx?pn=24G-P5-4988-KR",
        limitedNumber = "",
        price = 20000,
        warranty = "全球保固： 3 年",
        favorite = false
    )
) {
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .fillMaxHeight(0.5f),
        shape = MaterialTheme.shapes.extraLarge
    ) {
        Image(imgUrl = gpuProduct.imgUrl)
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Title(text = gpuProduct.name)
            PriceAndStore(
                modifier = Modifier.align(Alignment.End),
                price = gpuProduct.price,
                buyable = gpuProduct.canBeBought
            )
        }
    }
}