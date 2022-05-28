package com.rulhouse.evgawatcher.presentation

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    Text(
        text = "Test"
    )
    LazyColumn(
        
    ) {
        val products = viewModel.products.value
        if (products != null) {
            items(products) { item ->
                GpuProductItem(item = item)
            }
        }
    }
}