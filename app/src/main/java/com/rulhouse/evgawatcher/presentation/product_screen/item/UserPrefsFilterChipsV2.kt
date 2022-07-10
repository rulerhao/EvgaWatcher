package com.rulhouse.evgawatcher.presentation.product_screen.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.screen.BooleanFilterChip

@Composable
fun UserPrefsFilterChipsV2(
    modifier: Modifier,
    filtersState: UserPreferencesState,
    onEvent: (ProductsScreenEvent) -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
    ) {
        BooleanFilterChip(
            isOn = filtersState.showingOutOfStock,
            onClick = {
                onEvent(ProductsScreenEvent.OnShowingOutOfStockChanged)
            },
            Content = {
                Text(text = context.getString(R.string.show_unbuyable))
            }
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        AnimatedVisibility(visible = filtersState.showingOutOfStock) {
            BooleanFilterChip(
                isOn = filtersState.showingNoPrice,
                onClick = {
                    onEvent(ProductsScreenEvent.OnShowingNoPriceChanged)
                },
                Content = {
                    Text(text = context.getString(R.string.show_no_price))
                }
            )
        }
    }
}