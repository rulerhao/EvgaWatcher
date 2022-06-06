package com.rulhouse.evgawatcher.presentation.product_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.rulhouse.evgawatcher.presentation.products_screen.GpuProductItem
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductScreen(
//    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val gpuProduct = viewModel.gpuProduct.value
//    LazyColumn() {
//        items(listOf(gpuProduct)) { item ->
//            GpuProductItem(item = item, onClick = {})
//        }
//    }
//    GpuProductItem(item = gpuProduct, onClick = {})
    Column(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                onClick = {
                    viewModel.onEvent(ProductScreenEvent.ToggleFavoriteButton)
                }
            ) {
                Icon(
                    imageVector = if (viewModel.favoriteGpuProduct.value != null) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        GlideImage(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp),
            imageModel = gpuProduct.imgUrl,
            contentScale = ContentScale.Fit,
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Text(
                    text = "image request failed."
                )
            }
        )
        Text(
            text = gpuProduct.name,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = gpuProduct.serial,
            color = MaterialTheme.colors.primary
        )
    }
}