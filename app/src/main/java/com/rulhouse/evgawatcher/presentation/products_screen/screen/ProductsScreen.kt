package com.rulhouse.evgawatcher.presentation.products_screen.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.event.CrawlerProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.main_scaffold.MainScaffold
import com.rulhouse.evgawatcher.presentation.product_screen.item.UserPrefsFilterChips
import com.rulhouse.evgawatcher.presentation.product_screen.item.UserPrefsFilterChipsV2
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.item.filter_area.FilterArea
import com.rulhouse.evgawatcher.presentation.products_screen.item.products_list.ProductsCardList
import com.rulhouse.evgawatcher.presentation.products_screen.model.ProductState
import com.rulhouse.evgawatcher.presentation.products_screen.view_model.ProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.screen.BottomNavigationBar

@Composable
fun ProductsScreen(
    title: String = "",
    viewModel: ProductsScreenViewModel,
    navController: NavController,
    productState: ProductState = ProductState.Success
) {
    MainScaffold(
        navController = navController,
        topBar = {
            Column(

            ) {
                SmallTopAppBar(
                    title = {
                        Text(text = title)
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
                FilterArea(
                    state = viewModel.userPreferencesState.value.filterState,
                    userPreferencesState = viewModel.userPreferencesState.value,
                    onEvent = { viewModel.onEvent(it) }
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center),
                visible = productState == ProductState.FinishedYet
            ) {
                CircularProgressIndicator()
            }
            AnimatedVisibility(
                modifier = Modifier
                    .align(Alignment.Center),
                visible = productState == ProductState.NetWorkError
            ) {
                Text(text = "Network error. Please reload it.")
            }
            Column() {
                ProductsCardList(
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
    }
}