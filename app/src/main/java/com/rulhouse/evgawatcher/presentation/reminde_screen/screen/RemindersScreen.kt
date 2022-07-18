package com.rulhouse.evgawatcher.presentation.reminde_screen.screen

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.gson.Gson
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.ProductsDifferenceWithReason
import com.rulhouse.evgawatcher.methods.notification_gpu_product_change.use_case.GetDifferenceProducts
import com.rulhouse.evgawatcher.presentation.Screen
import com.rulhouse.evgawatcher.presentation.crawler_products_screen.event.CrawlerProductsScreenEvent
import com.rulhouse.evgawatcher.presentation.main_scaffold.MainScaffold
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.ReminderMessageEvent
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.RemindersScreenEvent
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.CrawlerState
import com.rulhouse.evgawatcher.presentation.reminde_screen.util.ReminderScreenMethods
import com.rulhouse.evgawatcher.presentation.reminde_screen.view_model.ReminderMessagesViewModel
import com.rulhouse.evgawatcher.presentation.reminde_screen.view_model.RemindersScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RemindersScreen(
    navController: NavController = rememberNavController(),
    viewModel: RemindersScreenViewModel = hiltViewModel(),
    reminderMessagesViewModel: ReminderMessagesViewModel
) {
    MainScaffold(
        navController = navController,
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.reminder_top_app_bar))
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            RemindItem(
                reminderState = viewModel.workScheduled.value,
                onCheckedChange = {
                    viewModel.onEvent(RemindersScreenEvent.OnWorkManagerSwitchClick)
                }
            )
            ReminderMessagesArea(
                items = reminderMessagesViewModel.differenceProducts.value,
                onGetAll = { reminderMessagesViewModel.onEvent(ReminderMessageEvent.OnGetAll) },
                onGetIt = { reminderMessagesViewModel.onEvent(ReminderMessageEvent.OnGetIt(it)) },
                onClick = {
                    ReminderScreenMethods().navigateToProductScreen(
                        differenceProducts = reminderMessagesViewModel.differenceProducts.value,
                        navController = navController,
                        index = it
                    )
                },
                crawlerState = reminderMessagesViewModel.screenState.value.crawlerState,
                onRefresh = { reminderMessagesViewModel.onEvent(ReminderMessageEvent.OnRefresh) }
            )
        }
    }

}