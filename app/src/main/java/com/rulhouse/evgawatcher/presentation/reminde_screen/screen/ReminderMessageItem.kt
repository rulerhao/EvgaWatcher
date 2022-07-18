package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason

@Composable
fun ReminderMessageItem(
    item: ProductsDifferenceWithReason,
    index: Int,
    onGetIt: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onClick(index) }
    ) {
        Column() {
            if (item.reason == DifferenceReason.PriceGetHigher || item.reason == DifferenceReason.PriceGetLower) {
                PriceChangedView(
                    reason = item.reason,
                    prePrice = item.productBeCompare.price!!,
                    postPrice = item.productGoCompare.price!!
                )
            }
            if (item.reason == DifferenceReason.GetBuyable || item.reason == DifferenceReason.GetNotBuyable) {
                BuyableStateGetChanged(
                    reason = item.reason
                )
            }
            Text(
                text = item.productBeCompare.name,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                style = MaterialTheme.typography.bodyMedium
            )
            Button(
                modifier = Modifier
                    .align(Alignment.End),
                onClick = { onGetIt(index) }) {
                Text(text = stringResource(id = R.string.get_it))
            }
        }
    }
}

@Composable
private fun PriceChangedView(reason: DifferenceReason, prePrice: Int, postPrice: Int) {
    val reasonText = when (reason) {
        DifferenceReason.PriceGetHigher -> {
            stringResource(id = R.string.price_goes_up)
        }
        DifferenceReason.PriceGetLower -> {
            stringResource(id = R.string.price_goes_low)
        }
        else -> {
            ""
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = reasonText,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "$prePrice -> $postPrice",
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun BuyableStateGetChanged(reason: DifferenceReason) {
    val reasonText = when (reason) {
        DifferenceReason.GetBuyable -> {
            stringResource(id = R.string.get_in_stock)
        }
        DifferenceReason.GetNotBuyable -> {
            stringResource(id = R.string.get_out_of_stock)
        }
        else -> {
            ""
        }
    }

    Text(
        text = reasonText,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        style = MaterialTheme.typography.titleLarge
    )
}