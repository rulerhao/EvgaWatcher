package com.rulhouse.evgawatcher.presentation.products_screen.item

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rulhouse.evgawatcher.methods.StringMethods
import java.text.NumberFormat
import java.util.*

@Composable
fun PriceAndStore (
    modifier: Modifier,
    price: Int?,
    buyable: Boolean?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Price(price)
        Store(buyable)
    }
}

@Composable
private fun Price(price: Int?) {
    if (price != null) {
        if (price != 0) {
            Text(
                text = StringMethods.getNTFormat(price),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Composable
private fun Store(buyable: Boolean?) {
    if (buyable != null) {
        SmallFloatingActionButton(
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