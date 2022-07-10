package com.rulhouse.evgawatcher.presentation.products_screen.item.filter_area

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.data_store.user_preferences.data.UserPreferencesState
import com.rulhouse.evgawatcher.presentation.product_screen.item.UserPrefsFilterChipsV2
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterArea(
    state: Boolean,
    userPreferencesState: UserPreferencesState,
    onEvent: (ProductsScreenEvent) -> Unit
) {
    val context = LocalContext.current

    AnimatedVisibility(
        visible = state,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = context.getString(R.string.price),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = context.getString(R.string.ascending),
                        style = MaterialTheme.typography.titleSmall
                    )
                    RadioButton(
                        selected = userPreferencesState.priceAscending,
                        onClick = {
                            onEvent(ProductsScreenEvent.OnPriceSortedChanged(true))
                        }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = context.getString(R.string.descending),
                        style = MaterialTheme.typography.titleSmall
                    )
                    RadioButton(
                        selected = !userPreferencesState.priceAscending,
                        onClick = {
                            onEvent(ProductsScreenEvent.OnPriceSortedChanged(false))
                        }
                    )
                }
            }
            UserPrefsFilterChipsV2(
                modifier = Modifier,
                filtersState = userPreferencesState,
                onEvent = { onEvent(it) }
            )
        }
    }
}