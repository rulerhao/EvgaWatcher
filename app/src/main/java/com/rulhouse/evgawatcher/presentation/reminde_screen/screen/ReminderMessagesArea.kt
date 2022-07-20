package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.*
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
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.CrawlerState
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.ReminderScreenCrawlerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

@Composable
fun ReminderMessagesArea(
    items: List<ProductsDifferenceWithReason>?,
    onGetAll: () -> Unit,
    onGetIt: (Int) -> Unit,
    onClick: (Int) -> Unit,
    onRefresh: () -> Unit,
    crawlerState: CrawlerState = CrawlerState.Waiting
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        when(crawlerState) {
            CrawlerState.Waiting -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center),
                )
            }
            CrawlerState.Failure -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.network_error),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Button(
                        onClick = {
                            onRefresh()
                        }
                    ) {
                        Text(text = stringResource(id = R.string.network_refresh))
                    }
                }
            }
            CrawlerState.Success -> {
                products(
                    modifier = Modifier
                        .align(Alignment.Center),
                    items = items,
                    onGetAll = { onGetAll() },
                    onGetIt = { onGetIt(it) },
                    onClick = { onClick(it) }
                )
            }
        }
    }
}

@Composable
private fun products(
    modifier: Modifier,
    items: List<ProductsDifferenceWithReason>?,
    onGetAll: () -> Unit,
    onGetIt: (Int) -> Unit,
    onClick: (Int) -> Unit
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
        Text(
            modifier = modifier,
            text = stringResource(id = R.string.no_product_change)
        )
    }
}