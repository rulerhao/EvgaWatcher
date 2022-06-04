package com.rulhouse.evgawatcher.presentation.products_screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.presentation.ExpandCollapseColumn
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.screen.MainScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.MainScreenViewModel

@Composable
fun ProductsScreen(
    viewModel: MainScreenViewModel,
    navController: NavController
) {
    LazyColumn(

    ) {
        if (viewModel.productsSortedBySerial.value != null && viewModel.productsSortedBySerialModel.value != null) {
            itemsIndexed(viewModel.productsSortedBySerial.value!!) { index, item ->
                ExpandCollapseColumn(
                    expandCollapseModel = viewModel.productsSortedBySerialModel.value!![index],
                    products = item,
                    onCollapsedStateChanged = {
                        viewModel.onEvent(MainScreenEvent.OnCollapseColumnStateChanged(index))
                    },
                    onClick = {
                        navController.navigate(Screen.ProductScreen.route)
                    }
                )
            }
        }
    }
}