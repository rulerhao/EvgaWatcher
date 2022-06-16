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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductScreen(
//    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductScreenViewModel = hiltViewModel(),
    navController: NavController
) {
    val gpuProduct = viewModel.gpuProduct.value
    LazyColumn() {
        items(listOf(gpuProduct)) { item ->
            ProductItem(
                isFavorite = viewModel.favoriteGpuProduct.value != null,
                gpuProduct = item,
                onFavoriteClick = {
                    viewModel.onEvent(ProductScreenEvent.ToggleFavoriteButton)
                }
            )
        }
    }
}

@Composable
private fun ProductItem(
    isFavorite: Boolean,
    gpuProduct: GpuProduct,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        FavoriteButton(isFavorite = isFavorite, onFavoriteClick = { onFavoriteClick() })
        Serial(gpuProduct.serial)
        Picture(gpuProduct.imgUrl!!)
        Name(gpuProduct.name)
        Statements(gpuProduct.statement)
        Warranty(gpuProduct.warranty)
        LimitedNumber(gpuProduct.limitedNumber)
        Price(gpuProduct.price!!)
    }
}
@Composable
private fun FavoriteButton(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            onClick = {
                onFavoriteClick()
            }
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite",
                tint = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
private fun Picture(imgUrl: String) {
    GlideImage(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp),
        imageModel = imgUrl,
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
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        color = MaterialTheme.colors.primary
    )
}

@Composable
private fun Serial(serial: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            text = serial,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
private fun Statements(statements: List<String?>) {
    Column() {
        repeat(statements.size) {
            Text(text = statements[it]!!)
        }
    }
}

@Composable
private fun Warranty(
    warranty: String
) {
    Text(
        text = warranty
    )
}

@Composable
private fun LimitedNumber(
    limitedNumber: String
) {
    Text(
        text = limitedNumber
    )
}

@Composable
private fun Price(
    price: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        if (price != 0)
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = price.toString(),
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h5
            )
    }
}