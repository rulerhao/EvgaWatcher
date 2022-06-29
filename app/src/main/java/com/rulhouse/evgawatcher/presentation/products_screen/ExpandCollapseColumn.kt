package com.rulhouse.evgawatcher.presentation.products_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.favorite_products.feature_node.data.GpuProduct
import com.rulhouse.evgawatcher.presentation.products_screen.item.ProductItem

@Composable
fun ExpandCollapseColumn(
    expandCollapseModel: ExpandCollapseModel,
    onCollapsedStateChanged: (isChecked: Boolean) -> Unit,
    products: List<GpuProduct>,
    onClick: (GpuProduct) -> Unit,
    onFavoriteClick: (GpuProduct) -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onCollapsedStateChanged(!expandCollapseModel.isOpen)
                },
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colors.primary),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = expandCollapseModel.title,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.h6
                )
                ToggleButton(expandCollapseModel)
            }
        }
        ProductsList(
            expandCollapseModel = expandCollapseModel,
            products = products,
            onClick = {
                onClick(it)
            },
            onFavoriteClick = {
                onFavoriteClick(it)
            }
        )
        Divider(thickness = 0.5.dp)
    }
}

@Composable
private fun ProductsList(
    expandCollapseModel: ExpandCollapseModel,
    products: List<GpuProduct>,
    onClick: (GpuProduct) -> Unit,
    onFavoriteClick: (GpuProduct) -> Unit
) {

    val uriHandler = LocalUriHandler.current

    AnimatedVisibility(visible = expandCollapseModel.isOpen) {
        Column(

        ) {
            repeat(products.size) { index ->
                val item = products[index]
                Spacer(modifier = Modifier.size(8.dp))
                ProductItem(
                    gpuProduct = item,
                    onClick = {
                        onClick(it)
                    },
                    onFavoriteClick = {
                        onFavoriteClick(it)
                    },
                    onStoreClick = {
                        it.uri?.let { uri ->
                            uriHandler.openUri(uri)
                        }
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
private fun ToggleButton(
    expandCollapseModel: ExpandCollapseModel
) {
    Icon(
        modifier = Modifier
            .padding(8.dp)
            .size(36.dp),
        imageVector = if (expandCollapseModel.isOpen) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
        contentDescription = null,
        tint = MaterialTheme.colors.onPrimary
    )
}
