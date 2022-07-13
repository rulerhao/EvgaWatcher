package com.rulhouse.evgawatcher.presentation.product_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.crawler.crawler.util.GpuProductsMethods
import com.rulhouse.evgawatcher.methods.favorite_products.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.item.StoreAndFavorite
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.util.StringMethods
import com.rulhouse.evgawatcher.util.UriHandler
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ProductScreen(
    viewModel: ProductScreenViewModel = hiltViewModel(),
    navController: NavController = rememberNavController()
) {

    val context = LocalContext.current

    val gpuProduct = viewModel.gpuProduct.value

    Scaffold(
        modifier = Modifier
            .statusBarsPadding(),
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = GpuProductsMethods.getNameBySerial(gpuProduct.name))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
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
            .padding(8.dp)
    ) {
        PictureAndIcon(
            gpuProduct.imgUrl!!,
            content = { modifier ->
                StoreAndFavorite(
                    modifier = modifier,
                    favorite = isFavorite,
                    buyable = gpuProduct.canBeBought,
                    onFavoriteClick = {
                        onFavoriteClick()
                    },
                    onStoreClick = {
                        onStoreClick()
                    }
                )
            }
        )
        Name(gpuProduct.name)
        Spacer(modifier = Modifier.height(8.dp))
        Serial(gpuProduct.serial)
        Spacer(modifier = Modifier.height(8.dp))
        Statements(gpuProduct.statement)
        Spacer(modifier = Modifier.height(8.dp))
        Warranty(gpuProduct.warranty)
        Spacer(modifier = Modifier.height(8.dp))
        LimitedNumber(gpuProduct.limitedNumber)
        Spacer(modifier = Modifier.height(8.dp))
        Price(gpuProduct.price!!)
        GoToStoreCard(
            modifier = Modifier
                .align(Alignment.End),
            onClick = {
                onStoreClick()
            }
        )
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
private fun PictureAndIcon(
    imgUrl: String,
    content: @Composable (modifier: Modifier) -> Unit
) {
    Box() {
        content(
            modifier = Modifier
                .align(Alignment.TopEnd)
        )
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
}

@Preview
@Composable
private fun Name(
    name: String = "EVGA 3060 Ti"
) {
    Text(
        text = name,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun Serial(serial: String) {
    val text = stringResource(id = R.string.serial) + ":$serial"
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Statements(statements: List<String?>) {
    Column() {
        repeat(statements.size) {
            val point = if (it.mod(2) == 0) "●" else "○"
            Text(
                text = " $point ${statements[it]!!}",
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun Warranty(
    warranty: String
) {
    Text(
        text = warranty,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
private fun LimitedNumber(
    limitedNumber: String
) {
    Text(
        text = limitedNumber,
        color = MaterialTheme.colorScheme.onErrorContainer,
        style = MaterialTheme.typography.bodyMedium
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
                text = StringMethods.getNTFormat(price),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.titleLarge
            )
    }
}

@Composable
private fun GoToStoreCard(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        }
    ) {
        Icon(
            modifier = Modifier,
            imageVector = Icons.Default.Storefront,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = R.string.buy_button_description)
        )
    }
}