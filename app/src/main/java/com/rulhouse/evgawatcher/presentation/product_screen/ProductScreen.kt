package com.rulhouse.evgawatcher.presentation.product_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.products_screen.ProductsScreenViewModel
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductScreen(
//    mainScreenViewModel: MainScreenViewModel,
    viewModel: ProductScreenViewModel = hiltViewModel(),
    navController: NavController
) {
//    LaunchedEffect(key1 = true) {
//        viewModel.eventFlow.collectLatest { event ->
//            when (event) {
//                is ProductsScreenViewModel.UiEvent.OnCollapseColumnStateChanged -> {
//                    mainScreenViewModel.onEvent(MainScreenEvent.OnCollapseColumnStateChanged(event.index))
//                }
//            }
//        }
//    }

//    LazyColumn(
//
//    ) {
//        if (mainScreenViewModel.productsSortedBySerial.value != null && mainScreenViewModel.productsSortedBySerialModel.value != null) {
//            itemsIndexed(mainScreenViewModel.productsSortedBySerial.value!!) { index, item ->
//                ExpandCollapseColumn(
//                    expandCollapseModel = mainScreenViewModel.productsSortedBySerialModel.value!![index],
//                    products = item,
//                    onCollapsedStateChanged = {
//                        mainScreenViewModel.onEvent(
//                            MainScreenEvent.OnCollapseColumnStateChanged(index)
//                        )
//                    },
//                    onClick = {
//                        navController.navigate(Screen.ProductScreen.route)
//                    }
//                )
//            }
//        }
//    }
}