package com.rulhouse.evgawatcher.presentation.crawler_products_screen.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StoreAndFavorite(
    favorite: Boolean?,
    onFavoriteClick: () -> Unit,
    buyable: Boolean?,
    onStoreClick: () -> Unit,
) {
    Row() {
        Store(
            buyable = buyable,
            onClick = {
                onStoreClick()
            }
        )
        Favorite(
            favorite = favorite,
            onClick = {
                onFavoriteClick()
            }
        )
    }
}

@Composable
fun Favorite(
    favorite: Boolean?,
    onClick: () -> Unit
) {
    if (favorite != null) {
        val imageVector = if (favorite) {
            Icons.Default.Favorite
        } else {
            Icons.Default.FavoriteBorder
        }
            SmallFloatingActionButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = imageVector,
                    contentDescription = null
                )
            }
    }
}

@Composable
private fun Store(
    buyable: Boolean?,
    onClick: () -> Unit
) {
    if (buyable != null) {
        if (buyable) {
            SmallFloatingActionButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector = Icons.Default.Storefront,
                    contentDescription = null
                )
            }
        }
    }
}