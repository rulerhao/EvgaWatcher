package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rulhouse.evgawatcher.R
import com.rulhouse.evgawatcher.presentation.reminde_screen.event.RemindersScreenEvent

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RemindersScreen(
    navController: NavController = rememberNavController(),
    viewModel: RemindersScreenViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = context.getString(R.string.reminder_top_app_bar))
                }
            )
        }
    ) {
        RemindItem(
            reminderState = viewModel.workScheduled.value,
            onCheckedChange = {
                viewModel.onEvent(RemindersScreenEvent.OnWorkManagerSwitchClick)
            }
        )
    }
}

@Composable
private fun floatingAddButton(
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = {
            onClick()
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
//            tint = SlackCloneColor
        )
    }
}