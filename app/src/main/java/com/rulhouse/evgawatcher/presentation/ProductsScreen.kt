package com.rulhouse.evgawatcher.presentation

import android.util.Log
import androidx.compose.animation.core.repeatable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.rulhouse.evgawatcher.GpuProduct

@Composable
fun ProductsScreen(
    viewModel: MainScreenViewModel,
    navController: NavController
) {
    LazyColumn(

    ) {
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