package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.main_scaffold.MainScaffold
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.RemindersScreenEvent
import com.rulhouse.evgawatcher.presentation.screen.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RemindersScreen(
    navController: NavController = rememberNavController(),
    viewModel: RemindersScreenViewModel = hiltViewModel()
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
        Box(
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
        }
    }
}