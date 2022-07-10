package com.rulhouse.evgawatcher.presentation.product_screen.item

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.model.BooleanFilterChipModel
import com.rulhouse.evgawatcher.presentation.products_screen.item.boolean_filter_chip.screen.BooleanFilterChipsRow
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent

@Composable
fun UserPrefsFilterChips(
    filters: UserPreferencesState,
    onEvent: (ProductsScreenEvent) -> Unit
) {
    val context = LocalContext.current

    val eventList = remember{ mutableStateOf(
        listOf(
            ProductsScreenEvent.OnShowingOutOfStockChanged,
            ProductsScreenEvent.OnPriceAscendingChanged,
            ProductsScreenEvent.OnShowingNoPriceChanged
        )
    )
    }

    BooleanFilterChipsRow(
        list = listOf(
            BooleanFilterChipModel(
                isOn = filters.showingOutOfStock,
                text = context.getString(R.string.show_unbuyable)
            ),
            BooleanFilterChipModel(
                isOn = filters.priceAscending,
                text = context.getString(R.string.price_ascending)
            ),
            BooleanFilterChipModel(
                isOn = filters.showingNoPrice,
                text = context.getString(R.string.show_no_price)
            ),
        ),
        onClick = {
            onEvent(eventList.value[it])
        }
    )
}