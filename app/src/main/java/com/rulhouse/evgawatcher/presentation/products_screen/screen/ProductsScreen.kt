package com.rulhouse.evgawatcher.presentation.products_screen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.product_screen.item.BooleanFilterChipsImpl
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.item.products_list.ProductsCardList
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(
    viewModel: ProductsScreenViewModel,
    navController: NavController
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text("EVGA WATCHER")
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onEvent(ProductsScreenEvent.OnFilterStateChanged)
                        }
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = Icons.Default.Tune,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                BooleanFilterChipsImpl(
                    viewModel.userPreferencesState.value,
                    onEvent = { viewModel.onEvent(it) }
                )
                ProductsCardList(
                    viewModel = viewModel,
                    navController = navController
                )
            }
    }
}