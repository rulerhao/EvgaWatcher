package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.DifferenceReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReminderMessageItems(
    items: List<ProductsDifferenceWithReason>?,
    onGetIt: (Int) -> Unit,
    onClick: (Int) -> Unit
) {
    AnimatedContent(
        targetState = items) {
        LazyColumn() {
            if (items != null) {
                itemsIndexed(items) { index, item ->
                    ReminderMessageItem(
                        item,
                        index = index,
                        onClick = { onClick(it) },
                        onGetIt = { onGetIt(it) }
                    )
                }
            }
        }
    }
}