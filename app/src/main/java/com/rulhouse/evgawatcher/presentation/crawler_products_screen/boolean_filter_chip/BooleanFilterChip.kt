package com.rulhouse.evgawatcher.presentation.crawler_products_screen.boolean_filter_chip

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BooleanFilterChip(
    isOn: Boolean,
    onClick: () -> Unit,
    Content: @Composable () -> Unit
) {
    AnimatedContent(targetState = isOn) {
        FilterChip(
            modifier = Modifier
                .padding(8.dp),
            selected = isOn,
            onClick = {
                onClick()
            },
            label = {
                Content()
            }
        )
    }
}