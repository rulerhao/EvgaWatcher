package com.rulhouse.evgawatcher.presentation.crawler_products_screen.item

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rulhouse.evgawatcher.methods.StringMethods

@Composable
fun Price(price: Int?) {
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