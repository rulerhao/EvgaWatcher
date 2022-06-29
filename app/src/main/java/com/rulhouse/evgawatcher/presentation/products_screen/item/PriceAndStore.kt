package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PriceAndStore (
    modifier: Modifier,
    price: Int?,
    buyable: Boolean?
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Store(buyable)
        Price(price)
    }
}

@Composable
private fun Price(price: Int?) {
    if (price != null) {
        if (price != 0) {
            Text(
                text = price.toString(),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun Store(buyable: Boolean?) {
    if (buyable != null) {
        val tint = if (buyable) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onPrimaryContainer
        }
        Button(
            onClick = {

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