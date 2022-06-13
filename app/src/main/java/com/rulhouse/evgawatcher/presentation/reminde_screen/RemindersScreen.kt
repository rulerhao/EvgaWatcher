package com.rulhouse.evgawatcher.presentation.reminde_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RemindersScreen(
    navController: NavController = rememberNavController(),
    remindersScreenViewModel: RemindersScreenViewModel = hiltViewModel()
) {
    Scaffold(
        floatingActionButton = {
            floatingAddButton(
                onClick = {
                    RemindersScreenEvent.OnAddClick
                }
            )
        }
    ) {
        Column(

        ) {
            RepeatedRemindItem()
        }
    }
}

@Composable
private fun floatingAddButton(
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
        backgroundColor = Color.White
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
//            tint = SlackCloneColor
        )
    }
}