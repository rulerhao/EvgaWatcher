package com.rulhouse.evgawatcher.presentation.product_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.crawler.feature_node.domain.use_case.FavoriteGpuProductUseCases
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductScreen(
//    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val gpuProduct = viewModel.gpuProduct.value
    Column(

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