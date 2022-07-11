package com.rulhouse.evgawatcher.presentation.product_screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.item.StoreAndFavorite
import com.rulhouse.evgawatcher.util.UriHandler
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    val gpuProduct = viewModel.gpuProduct.value

    LazyColumn() {
        items(listOf(gpuProduct)) { item ->
            ProductItem(
                isFavorite = viewModel.favoriteGpuProduct.value != null,
                gpuProduct = item,
                onFavoriteClick = {
                    viewModel.onEvent(ProductScreenEvent.ToggleFavoriteButton)
                },
                onStoreClick = {
                    UriHandler().openStore(context, gpuProduct.uri)
                }
            )
        }
    }
}

@Composable
private fun ProductItem(
    isFavorite: Boolean,
    gpuProduct: GpuProduct,
    onFavoriteClick: () -> Unit,
    onStoreClick: () -> Unit
) {
    Column(
        modifier = Modifier
    ) {
        StoreAndFavorite(
            favorite = isFavorite,
            onFavoriteClick = {
                onFavoriteClick()
            },
            buyable = gpuProduct.canBeBought,
            onStoreClick = {
                onStoreClick()
            }
        )
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
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        FavoriteButton(
            modifier = Modifier
                .align(Alignment.CenterEnd),
            isFavorite = isFavorite,
            onFavoriteClick = {
                onFavoriteClick()
            }
        )
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
        color = MaterialTheme.colorScheme.primary
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
            color = MaterialTheme.colorScheme.primary
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
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge
            )
    }
}