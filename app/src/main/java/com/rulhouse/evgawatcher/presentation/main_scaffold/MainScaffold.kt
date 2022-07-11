package com.rulhouse.evgawatcher.presentation.main_scaffold

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rulhouse.evgawatcher.presentation.products_screen.event.ProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.products_screen.item.filter_area.FilterArea
import com.rulhouse.evgawatcher.presentation.products_screen.item.products_list.ProductsCardList
import com.rulhouse.evgawatcher.presentation.screen.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    navController: NavController = rememberNavController(),
    topBar: @Composable () -> Unit,
    Content: @Composable (innerPadding: PaddingValues) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        topBar = {
            topBar()
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        Content(innerPadding)
    }
}