package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifference
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun ReminderMessagesArea(
    items: List<ProductsDifferenceWithReason>?,
    onGetAll: () -> Unit,
    onGetIt: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
    ) {
        val showingState = items != null && items.isNotEmpty()

        if (showingState) {
            Column() {
                Button(
                    modifier = Modifier
                        .align(Alignment.End),
                    onClick = { onGetAll() }) {
                    Text(text = stringResource(id = R.string.get_it_all))
                }
                ReminderMessageItems(
                    items,
                    onGetIt = { onGetIt(it) },
                    onClick = { onClick(it) }
                )
            }
        } else {
            Text(text = stringResource(id = R.string.no_product_change))
        }
    }
}