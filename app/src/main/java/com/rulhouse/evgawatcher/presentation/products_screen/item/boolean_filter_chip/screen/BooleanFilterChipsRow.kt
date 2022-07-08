package com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.screen

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.model.BooleanFilterChipModel

@Composable
fun BooleanFilterChipsRow(
    list: List<BooleanFilterChipModel>,
    onClick: (Int) -> Unit
) {
    LazyRow() {
        itemsIndexed(list) { index, item ->
            BooleanFilterChip(
                isOn = item.isOn,
                onClick = {
                    onClick(index)
                },
                Content = {
                    Text(text = item.text)
                }
            )
        }
    }
}